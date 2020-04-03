package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection  {
	private static String DB_URL = "jdbc:mysql://localhost:3306/corona_database?autoconnect=true&useSSl=false";
	private static String USER_NAME = "root";
	private static String PASSWORD = "12345678";
	
public DatabaseConnection() {
	
}

	protected static void printSQLException(SQLException errors) {
		for (Throwable e : errors) {
			if (e instanceof SQLException) {
				e.printStackTrace();
				System.err.println(e.getMessage());
				Throwable t = errors.getCause();
				if (t != null) {
					System.out.println("Caused by " + t);
					t = t.getCause();
				}
			}
		}
	}

public static Connection getConnection() {
	Connection con = null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
		System.out.println("Connection to mysql successfully!");
		
	} catch(Exception e) {
		System.out.println("Connection to the database failed!");
		e.printStackTrace();
	}
	return con;
}


}
