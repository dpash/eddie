package uk.org.catnip.eddie.spider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;

public class FeedDesc implements SQLObjectDesc {
	public String tablename() {return "feeds"; }
	public String classname() {return getClassObject().getName();}
	public Class getClassObject() {return Feed.class;}
	public Feed newObject() {return new Feed();}
	public String id_column() {return "feed_id";}
	public SQLObject mapSQL(ResultSet rs, SQLObject object) throws SQLException {
		
		Feed feed = null;
		if (object == null) {
			feed = newObject();
		} else {
			feed = (Feed)object;
		}
        feed.setId(rs.getInt("feed_id"));
        feed.setFeedUrl(rs.getString("feed_url"));
        feed.setLastChecked(rs.getTimestamp("last_checked"));
        feed.setNextCheck(rs.getTimestamp("next_check"));
        feed.setLastModified(rs.getTimestamp("last_modified"));
        return feed;
	}
	private  Map<String, Class> columns = genColumns();
	public  Map<String, Class> getColumns() { return columns; }
	private  Map<String, Class> genColumns() {
		Map<String, Class> cols = new HashMap<String, Class>();
		cols.put("feed_url", String.class);       
		cols.put("last_checked",java.util.Date.class);
		cols.put("last_modified", java.util.Date.class);
		cols.put("next_check", java.util.Date.class);		
		return cols;
	}

	

}
