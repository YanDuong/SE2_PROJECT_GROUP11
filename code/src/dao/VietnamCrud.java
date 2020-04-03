package dao;
import model.VietNam;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
public class VietnamCrud {
public boolean updateStatistics(VietNam v) {
	
	boolean isUpdated = false;
	Connection conn = DatabaseConnection.getConnection();
	String sql = "update vietnam set totalOfInfected = ?, totalOfRecovered = ?, totalOfDeaths = ?, updatedInfected = ?, updatedRecovered = ?, updatedDeaths = ?;";
	PreparedStatement ps = null;
	try {
		ps = conn.prepareStatement(sql);
		ps.setInt(1, v.getTotalOfInfected());
		ps.setInt(2, v.getTotalOfRecovered());
		ps.setInt(3, v.getTotalOfDeaths());
		ps.setInt(4, v.getUpdatedInfected());
		ps.setInt(5, v.getUpdatedRecovered());
		ps.setInt(6, v.getUpdatedDeaths());
		int row = ps.executeUpdate();
		isUpdated = row > 0;
		
		
		
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
	
	return isUpdated;
	
}


}
