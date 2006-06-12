package uk.org.catnip.eddie.spider;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

public class Feed extends SQLObject {
	private static Logger log = Logger.getLogger(Feed.class);

	SQLObjectDesc description = new FeedDesc();
	private String feedUrl;
	private Date lastModified;
	private Date lastChecked;
	private Date nextCheck;
	public Feed() {}
	public Feed(int id) {
		// TODO: fill this feed in from the database
	}
	public String getFeed_url() {
		return feedUrl;
	}
	public void setFeedUrl(String feedUrl) {
		this.feedUrl = feedUrl;
	}

	public Date getLast_checked() {
		return lastChecked;
	}
	public void setLastChecked(Date lastChecked) {
		this.lastChecked = lastChecked;
	}
	
	public List<SQLObject> getEntries() {
		return Entry.retrieveWhere("feed = ?", getId());
	}
	
	public void update() { super.update(description); }

	public Date getNext_check() {
		return nextCheck;
	}
	
	public void setNextCheck(Date nextCheck) {
		this.nextCheck = nextCheck;
	}
	
	public void scheduleNextCheck() {
		Date now = new Date();
		now.setTime(now.getTime() + 3600000); // Check again in an hour
		setNextCheck(now);
	}
	public Date getLast_modified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	static List<SQLObject> retrieveWhere(String where_clause, Object...parameters) {
		return SQLObject.retrieveWhere(new FeedDesc(), where_clause, parameters);
	}
}
