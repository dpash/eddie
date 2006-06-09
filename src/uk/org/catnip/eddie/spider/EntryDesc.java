package uk.org.catnip.eddie.spider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;

public class EntryDesc implements SQLObjectDesc {
	public String tablename() {return "entries"; }
	public String classname() {return getClassObject().getName();}
	public Class getClassObject() {return Entry.class;}
	public Entry newObject() {return new Entry();}
	public String id_column() {return "entry_id";}
	private  Map<String, Class> columns = genColumns();
	public  Map<String, Class> getColumns() { return columns; }
	private  Map<String, Class> genColumns() {
		Map<String, Class> cols = new HashMap<String, Class>();
		cols.put("link", String.class);       
		cols.put("content", String.class);  
		cols.put("created",java.util.Date.class);
		cols.put("modified", java.util.Date.class);
		cols.put("issued", java.util.Date.class);
		cols.put("guid", String.class);
		cols.put("title", String.class);  
		cols.put("author_name", String.class);
		cols.put("author_url", String.class);
		cols.put("author_email", String.class);
		cols.put("feed", Feed.class);
		
		return cols;
	}
	

	public SQLObject mapSQL(ResultSet rs, SQLObject object) throws SQLException {
		
		Entry entry = null;
		if (object == null) {
			entry = newObject();
		} else {
			entry = (Entry)object;
		}
        entry.setId(rs.getInt("entry_id"));
        entry.setLink(rs.getString("link"));       
   	 	entry.setContent(rs.getString("content"));  
   	    entry.setCreated(rs.getDate("created"));
   	    entry.setModified(rs.getDate("modified"));
   	    entry.setIssued(rs.getDate("issued"));
   	    entry.setGuid(rs.getString("guid"));
   	    entry.setTitle(rs.getString("title"));  
   	    entry.setAuthor_name(rs.getString("author_name"));
   	    entry.setAuthor_url(rs.getString("author_url"));
   	    entry.setAuthor_email(rs.getString("author_email"));
   	    entry.setFeed(new Feed(rs.getInt("feed")));

        return entry;
	}
}
