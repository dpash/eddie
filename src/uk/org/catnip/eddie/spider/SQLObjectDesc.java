package uk.org.catnip.eddie.spider;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
public interface SQLObjectDesc {
	public String tablename();
	public String classname();
	public Class getClassObject();
	public Object newObject();
	public String id_column(); 
	public Map<String, Class> getColumns();
	public SQLObject mapSQL(ResultSet rs, SQLObject object) throws SQLException;
}
