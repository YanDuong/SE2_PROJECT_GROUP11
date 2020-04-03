package dao;
import model.World;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
public class WorldCrud {
public boolean updateStatistics(World w) {
	boolean isUpdated = false;
	Connection conn = DatabaseConnection.getConnection();
	PreparedStatement ps = null;
	try {
		ps = conn.prepareStatement("update world set totalOfInfected = ?, totalOfRecovered = ?, totalOfDeaths = ?, updatedInfected = ?, updatedRecovered = ?, updatedDeaths = ?;");
		ps.setInt(1, w.getTotalOfInfected());
		ps.setInt(2, w.getTotalOfRecovered());
		ps.setInt(3, w.getTotalOfDeaths());
		ps.setInt(4, w.getUpdatedInfected());
		ps.setInt(5, w.getUpdatedRecovered());
		ps.setInt(6, w.getUpdatedDeaths());
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
