package uk.org.catnip.eddie.spider;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.*;
public class Database {
	private static Database ref;
    private Database()
    {
        // no code req'd
    }

    public static Database getDB()
    {
      if (ref == null)
          // it's ok, we can call this constructor
          ref = new Database();
      return ref;
    }
	private static Logger log = Logger.getLogger(Main.class);
	private Connection conn;
	
	
	private String username;
	private String password;
	private String database;
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void connect() {
		try {
			Class c = Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(database, username, password);
		} catch (ClassNotFoundException e) {
			log.info("failed to load driver", e);
		} catch (SQLException e) {
			log.info("database connection failed", e);
		}
	}
	public void disconnect() {
		try {
		conn.close();
		} catch (SQLException e) {
			log.info("database disconnection failed", e);
		}
		
	}
	public int getInt(String sql) {
		int result = 0;
		try {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		result = rs.getInt(1);
		rs.close();
		stmt.close();
		} catch (SQLException e) {
			log.info("database operation failed", e);
		}
		return result;
	}

	public List<String> getStringList(String sql) {
		List<String> result = new ArrayList<String>();
		try {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		result.add(rs.getString(1));
		rs.close();
		stmt.close();
		} catch (SQLException e) {
			log.info("database operation failed", e);
		}
		return result;
	}

	public Connection getConn() {
		return conn;
	}
}
