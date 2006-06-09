package uk.org.catnip.eddie.spider;

import java.util.*;
import java.sql.*;
import org.apache.log4j.Logger;
public class Entry extends SQLObject {
	private static Logger log = Logger.getLogger(Entry.class);

	SQLObjectDesc description = new EntryDesc();

	 String link;       
	 String content;  
		private java.util.Date created;
		private java.util.Date modified;
		private java.util.Date issued;
	 String guid;
	 String title;  
	 String author_name;
	 String author_url;
	 String author_email;
	 Feed feed;

	public String updateSQL() {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ");
		sb.append(description.tablename());
		sb.append(" SET ");

		
		sb.append("' WHERE ");
		sb.append(description.id_column());
		sb.append(" = ");
		sb.append(getId());
		sb.append(";");
		log.info(sb);
		return sb.toString();
	}
	
	public void update() {
		super.update(description);
	}

	static List<SQLObject> retrieveWhere(String where_clause, Object...parameters) {
		return SQLObject.retrieveWhere(new EntryDesc(), where_clause, parameters);
	}


	static Entry find_or_create(String where_clause, Object...parameters) {
		List<SQLObject> list = SQLObject.retrieveWhere(new EntryDesc(), where_clause, parameters);
		if (list.isEmpty()) {
			return (Entry)Entry.create(new EntryDesc());
		}
		return (Entry)list.get(0);
	}


	public String getAuthor_email() {
		return author_email;
	}



	public void setAuthor_email(String author_email) {
		this.author_email = author_email;
	}



	public String getAuthor_name() {
		return author_name;
	}



	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}



	public String getAuthor_url() {
		return author_url;
	}



	public void setAuthor_url(String author_url) {
		this.author_url = author_url;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public java.util.Date getCreated() {
		return created;
	}



	public void setCreated(java.util.Date created) {
		this.created = created;
	}



	public SQLObjectDesc getDescription() {
		return description;
	}



	public void setDescription(SQLObjectDesc description) {
		this.description = description;
	}



	public Feed getFeed() {
		return feed;
	}



	public void setFeed(Feed feed) {
		this.feed = feed;
	}



	public String getGuid() {
		return guid;
	}



	public void setGuid(String guid) {
		this.guid = guid;
	}



	public java.util.Date getIssued() {
		return issued;
	}



	public void setIssued(java.util.Date issued) {
		this.issued = issued;
	}



	public String getLink() {
		return link;
	}



	public void setLink(String link) {
		this.link = link;
	}



	public java.util.Date getModified() {
		return modified;
	}



	public void setModified(java.util.Date modified) {
		this.modified = modified;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}
}
