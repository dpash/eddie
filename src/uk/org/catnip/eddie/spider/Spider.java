package uk.org.catnip.eddie.spider;

import java.io.IOException;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import uk.org.catnip.eddie.FeedData;
import uk.org.catnip.eddie.parser.Parser;
import java.util.*;
import java.io.*;
import java.text.*;
import java.sql.Timestamp;

public class Spider {
	DateFormat httpDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z");
	static String[] feed_contenttypes_array = {
		"text/xml", 
		"application/rdf+xml",
		"application/rss+xml",
		"application/xml",
		"application/atom+xml",
		"text/rdf+xml"
		};
    static List feed_contenttypes = Arrays.asList(feed_contenttypes_array);
	private static final String USER_AGENT = "eddie-feedreader http://www.davidpashley.com/projects/eddie.html";
	private static Logger log = Logger.getLogger(Spider.class);
	private HttpClient client;
	private Database db;
	
	public  Spider() {
		//	 Create an instance of HttpClient.
		client = new HttpClient();
		client.getParams().setParameter("http.useragent", USER_AGENT);
		client.getParams().setParameter("http.socket.timeout", new Integer(3000));
		client.getParams().setParameter("http.connection.timeout", new Integer(3000));
		client.getParams().setParameter("http.protocol.head-body-timeout", new Integer(3000));
		client.getParams().setParameter("http.protocol.content-charset", "UTF-8");
	}
	
	public void run() {
	db = Database.getDB();
	db.setDatabase("jdbc:postgresql://kitty/satellite");
	db.setUsername("david");
	db.setPassword("ginrummy");
	db.connect();

	try {
		//while(true) {
			
	
			//List<SQLObject> feeds = Feed.retrieveWhere("next_check <= ?", new Timestamp((new Date()).getTime()));
			List<SQLObject> feeds = Feed.retrieveWhere("feed_id = ?", "1");
			Iterator it = feeds.iterator();
			while (it.hasNext()) {
				Feed feed = (Feed)it.next();
				try {
					fetch_feed(feed);
				} catch (NullPointerException e){
					log.warn("what the fuck?", e);
				}
			}
		//	Thread.sleep(next_run_in());
		//}
	//} catch (InterruptedException e) {
//		log.info("Interrupted:", e);
	} finally {
		db.disconnect();
	}
}

	public long next_run_in() {
		int time = db.getInt("select extract(epoch from min(next_check)-now()) from feeds;");
		if ( time < 60 ) { time = 60; }
		return time * 1000;
	}


public void fetch_feed(Feed feed) {
	log.info(feed);
		String url = feed.getFeed_url();
		log.info("starting download of " + url);

		GetMethod method = new GetMethod(url);
		log.info("feed currently has " + feed.getEntries().size()+" entries");
		try {
			if(feed.getLast_checked() != null) {
				
				String last_modified = httpDateFormat.format(feed.getLast_checked());
				log.info("If-Modified-Since: " +last_modified);
				method.setRequestHeader("If-Modified-Since",last_modified);
			}
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				log.warn("Method failed: " + method.getStatusLine());
			} else {
				String contentType = method.getResponseHeader("content-type").getValue().replaceAll(";.*$", "");
				
				if (feed_contenttypes.contains(contentType)) {
					String charset = method.getResponseCharSet();
					InputStreamReader reader = 
						new InputStreamReader(method.getResponseBodyAsStream(), charset);

					log.info("fetched feed. Starting parsing");

					Parser parser = new Parser();

					FeedData feeddata = parser.parse(reader);
					Iterator it = feeddata.entries();
					while (it.hasNext()) {
						uk.org.catnip.eddie.Entry entrydata = (uk.org.catnip.eddie.Entry)it.next();
						uk.org.catnip.eddie.spider.Entry entry = Entry.find_or_create("guid = ?", entrydata.get("id"));
						entry.setGuid(entrydata.get("id"));
						//Content content = (Content)entrydata.contents().next();
						//entry.setContent(content.getValue());
						if (entrydata.getAuthor() != null) {
							entry.setAuthor_email(entrydata.getAuthor().getEmail());
							entry.setAuthor_name(entrydata.getAuthor().getName());
							entry.setAuthor_url(entrydata.getAuthor().getHref());
						}
						//entry.setCreated(entrydata.getCreated());
						entry.setTitle(entrydata.get("title"));
						entry.setFeed(feed);
						log.info(entrydata);
						log.info(entry);
						entry.update();
					}
					//log.info(feeddata);
					
					try {
						String last_modified = method.getResponseHeader("Last-Modified").getValue();
					feed.setLastChecked(httpDateFormat.parse(last_modified));
					} catch (Exception e) {
						log.debug("failed to parse last-modified date", e);
					}
					
				} else {
					log.info("Unsupported Content Type: " + contentType);
				}

			}
		} catch (HttpException e) {
			log.info("Fatal protocol violation: " + e.getMessage(), e);
		} catch (IOException e) {
			log.info("Fatal transport error: " + e.getMessage(), e);
		} finally {
			// Release the connection.
			method.releaseConnection();
			feed.setLastChecked(new Date());
			feed.scheduleNextCheck();
			feed.update();
		}
	}
}
