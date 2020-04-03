package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class AdminChecker implements AdminInterface {
public AdminChecker() {
	
}

@Override
public boolean validateAdmin(String adminName, String password) {
	Connection conn = DatabaseConnection.getConnection();
	String sql = "select * from administrator where name = '" + adminName + "' and password = '" + password + "';";
	PreparedStatement ps = null;
	try {
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			conn.close();
			return true;
		}
		
	} catch(SQLException e) {
		DatabaseConnection.printSQLException(e);
	} finally {
		if(ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {	}
		}
		
		if(conn != null) {
			try {
				conn.close();
			} catch(SQLException e) {}
		}
	}
	return false;
}


@Override
public boolean checkAdmin(String adminName) {
	Connection conn = DatabaseConnection.getConnection();
	String sql = "select * from administrator where name = '" + adminName + "';";
	PreparedStatement ps = null;
	try {
		 ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			
			return true;
		}
	} catch(SQLException e) {
		DatabaseConnection.printSQLException(e);
	} finally {
		if(ps != null) {
			try {
				ps.close();
			} catch(SQLException e) {}
		}
		if(conn != null) {
			try {
			conn.close();
			} catch(SQLException e) {}
		}
	}
	return false;
}
	
}

