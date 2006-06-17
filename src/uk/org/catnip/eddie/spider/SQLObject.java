package uk.org.catnip.eddie.spider;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

class SQLObject {
	private static Logger log = Logger.getLogger(SQLObject.class);

	SQLObjectDesc description = null;
	public int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String updateSQL() { return null; }

	
	static SQLObject getNewObject() {
		return new SQLObject();
	}

	void reload() throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * from ");
			sql.append(description.tablename());
			sql.append(" WHERE ");
			sql.append(description.id_column());
			sql.append(" = ");
			sql.append(getId());
			sql.append(";");
			stmt = Database.getDB().getConn().createStatement();
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				description.mapSQL(rs,this);
			}
		} finally {
			rs.close();
			stmt.close();
		}
	}
	static List<SQLObject> retrieveAll(SQLObjectDesc desc) {
		return retrieveWhere(desc, null);
	}
	static List<SQLObject> retrieveWhere(SQLObjectDesc desc, String where_clause, Object...parameters) {
		List<SQLObject> objects = new ArrayList<SQLObject>();
		try {
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * from ");
			sql.append(desc.tablename());
			if (where_clause != null) {
				sql.append(" WHERE ");
				sql.append(where_clause);
			}
			sql.append(";");
			log.debug(sql.toString());
			PreparedStatement stmt = Database.getDB().getConn().prepareStatement(sql.toString());
			for (int index = 0; index < parameters.length; ++index) {
				stmt.setObject(index+1, parameters[index]);
			}
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				objects.add(desc.mapSQL(rs, null));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			log.info("SQL exception", e);
		}
		return objects;
	}
	public void update(SQLObjectDesc description) {
	
			try {
					
					StringBuilder sql = new StringBuilder();
					List<Object> values = new ArrayList<Object>();
					sql.append("UPDATE ");
					sql.append(description.tablename());
					sql.append(" SET ");
					Map<String, Class> columns = description.getColumns();
					Iterator it = columns.keySet().iterator();
					while (it.hasNext()) {
						String column = (String)it.next();
						
			            Class[] argTypes = {};
			            Object[] argValues = {};
			            StringBuilder methodName = new StringBuilder();
			            methodName.append("get");
			            methodName.append(column.substring(0,1).toUpperCase());
			            methodName.append(column.substring(1));
			            
			            Method getter;
						try {
							getter = this.getClass().getMethod(methodName.toString(), argTypes);

							if(columns.get(column).getGenericSuperclass() == SQLObject.class) {
								
								values.add(((SQLObject)getter.invoke(this, argValues)).getId());
							} else {
								values.add(getter.invoke(this, argValues));
							}
							sql.append(column);
							sql.append(" = ? ");
							if (it.hasNext()) {
								sql.append(", ");
							}
						
						} catch (NoSuchMethodException e) {
							log.debug("failed to find " + methodName);
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			            
					}
					sql.append(" WHERE ");
					sql.append(description.id_column());
					sql.append(" = ?;");
					values.add(this.getId());
					log.debug(sql.toString() + values);
					PreparedStatement stmt = Database.getDB().getConn().prepareStatement(sql.toString());
					for (int index = 0; index < values.size(); ++index) {
						if (java.util.Date.class.isInstance(values.get(index))) {
							stmt.setTimestamp(index+1, new java.sql.Timestamp(((java.util.Date)values.get(index)).getTime()));
						} else {
							stmt.setObject(index+1, values.get(index));
						}
					}
					
					int res = stmt.executeUpdate();
					stmt.close();
					if (res != 1) {
						log.warn("updated the wrong number of rows. got " + res + " rather than 1");
					}
				
			} catch (SQLException e) {
				log.info("SQL exception", e);
			}
	
	}
	private void insert(SQLObjectDesc description) {
		
		try {
				
				StringBuilder sql = new StringBuilder();
				List<Object> values = new ArrayList<Object>();
				sql.append("INSERT INTO ");
				sql.append(description.tablename());
				sql.append(" (");
				sql.append(description.id_column());
				sql.append(") VALUES (?);");
		           
				PreparedStatement stmt = Database.getDB().getConn().prepareStatement(sql.toString());
				stmt.setLong(1,this.getId() );
				log.debug(sql.toString() + values);
				int res = stmt.executeUpdate();
				stmt.close();
				if (res != 1) {
					log.warn("updated the wrong number of rows. got " + res + " rather than 1");
				}
			
		} catch (SQLException e) {
			log.info("SQL exception", e);
		}

}
	public static SQLObject create(SQLObjectDesc desc) {
		SQLObject object = null;
			object = (SQLObject) desc.newObject();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT nextval('");
		sql.append(desc.tablename());
		sql.append("_");
		sql.append(desc.id_column());
		sql.append("_seq');");
		object.setId(Database.getDB().getInt(sql.toString()));
		object.insert(desc);
		return object;
		
	}
}
