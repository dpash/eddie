package uk.org.catnip.eddie.spider;

import java.util.*;
import java.sql.*;
import org.apache.log4j.Logger;
public class Feed extends SQLObject {
	private static Logger log = Logger.getLogger(Feed.class);

	SQLObjectDesc description = new FeedDesc();
	private String feedUrl;
	private java.util.Date lastModified;
	private java.util.Date lastChecked;
	private java.util.Date nextCheck;
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

	public java.util.Date getLast_checked() {
		return lastChecked;
	}
	public void setLastChecked(java.util.Date lastChecked) {
		if (lastChecked != null) {
		 this.lastChecked = new java.sql.Timestamp(lastChecked.getTime());
		}
	}
	
	public List<SQLObject> getEntries() {
		return Entry.retrieveWhere("feed = ?", getId());
	}
	
	public String updateSQL() {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ");
		sb.append(description.tablename());
		sb.append(" SET ");
		sb.append("feed_url='");
		sb.append(feedUrl);
		sb.append("', last_checked='");
		sb.append(lastChecked);
		sb.append("', next_check='");
		sb.append(nextCheck);
		if (lastModified != null) { 
		sb.append("', last_modified='");
		sb.append(lastModified);
		}
		sb.append("' WHERE ");
		sb.append(description.id_column());
		sb.append(" = ");
		sb.append(getId());
		sb.append(";");
		log.info(sb);
		return sb.toString();
	}
	

	public static SQLObject getNewObject() { return new Feed(); }

	public void update() { super.update(description); }

	public java.util.Date getNext_check() {
		return nextCheck;
	}
	public void setNextCheck(java.util.Date nextCheck) {
		this.nextCheck = new java.sql.Timestamp(nextCheck.getTime());
	}
	public void scheduleNextCheck() {
		java.util.Date now = new java.util.Date();
		setNextCheck(new java.sql.Timestamp(now.getTime() + 3600*1000));
	}
	public java.util.Date getLast_modified() {
		return lastModified;
	}
	public void setLastModified(java.util.Date lastModified) {
		this.lastModified = lastModified;
	}
	static List<SQLObject> retrieveWhere(String where_clause, Object...parameters) {
		return SQLObject.retrieveWhere(new FeedDesc(), where_clause, parameters);
	}
}
