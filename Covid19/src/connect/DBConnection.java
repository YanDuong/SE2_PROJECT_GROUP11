package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection implements AutoCloseable{
	private static String DB_URL = "jdbc:mysql://localhost:8889/covid?useSSL=false";
	private static String USER_NAME = "root";
	private static String PASSWORD = "anhkhang226";
	private Connection conn = null;
	private DBConnection() {
		
	}
	private static class DBHelper   {
		private static final DBConnection dbhelper = new DBConnection();
	}
	public static DBConnection getDBHelper() {
		return DBHelper.dbhelper;
	}
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			return conn;
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public void close(){
		try {
			if(conn != null) {
				this.conn.close();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
}

