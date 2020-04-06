package dao;
import model.City;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
public class CityCrudImp implements CityCrud {
	@Override
	public void addCity(City c) {
	Connection conn = DatabaseConnection.getConnection();
	String sql = "insert into cities (name, totalOfInfected, totalOfRecovered, totalOfDeaths, updatedInfected, updatedRecovered, updatedDeaths) values (?,?,?,?,?,?,?);";
	PreparedStatement ps = null;
	try {
		 ps = conn.prepareStatement(sql);
		
		ps.setString(1, c.getName());
		ps.setInt(2, c.getTotalOfInfected());
		ps.setInt(3, c.getTotalOfRecovered());
		ps.setInt(4, c.getTotalOfDeaths());
		ps.setInt(5, c.getUpdatedInfected());
		ps.setInt(6, c.getUpdatedRecovered());
		ps.setInt(7, c.getUpdatedDeaths());
		ps.executeUpdate();
		
		
		
	}catch(SQLException e) {
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
}
	
	@Override
	public boolean updateCity(City c) {
		boolean isUpdated = false;
		Connection conn = DatabaseConnection.getConnection();
		String sql = "update cities set name = ?, totalOfInfected = ?, totalOfRecovered = ?, totalOfDeaths = ?, updatedInfected = ?, updatedRecovered = ?, updatedDeaths = ?;";
		PreparedStatement ps = null;
		try {
			 ps = conn.prepareStatement(sql);
			ps.setString(1, c.getName());
			ps.setInt(2, c.getTotalOfInfected());
			ps.setInt(3, c.getTotalOfRecovered());
			ps.setInt(4, c.getTotalOfDeaths());
			ps.setInt(5, c.getUpdatedInfected());
			ps.setInt(6, c.getUpdatedRecovered());
			ps.setInt(7, c.getUpdatedDeaths());
			int row = ps.executeUpdate();
			isUpdated = row > 0;
		}catch(SQLException e) {
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
		return  isUpdated;
		
		
		
	} 
	
	@Override
	public boolean deleteCity(int cityId) {
		boolean isDeleted = false;
		Connection conn = DatabaseConnection.getConnection();
		String sql = "delete from cities where city_id = ?";
		PreparedStatement ps = null;
		try {
			 ps = conn.prepareStatement(sql);
			ps.setInt(1, cityId);
			int row = ps.executeUpdate();
			isDeleted = row > 0;
			
			
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
		return isDeleted;
	}
	
	@Override
	public List<City> getAllCities() {
		List<City> cities = new ArrayList<>();
		Connection conn = DatabaseConnection.getConnection();
		String sql = "select * from cities";
		PreparedStatement ps = null;
		try {
			
			 ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int totalOfInfected = rs.getInt(3);
				int totalOfRecovered = rs.getInt(4);
				int totalOfDeaths = rs.getInt(5);
				int updatedInfected = rs.getInt(6);
				int updatedRecovered =rs.getInt(7);
				int updatedDeaths = rs.getInt(8);
				cities.add(new City(id, name, totalOfInfected, totalOfRecovered, totalOfDeaths, updatedInfected, updatedRecovered, updatedDeaths));
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
		return cities;
	}
	
	@Override
	public City getCity(int id) {
		Connection conn = DatabaseConnection.getConnection();
		String sql = "select * from cities where city_id = ?;";
		City c = null;
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				int totalOfInfected = rs.getInt("totalOfInfected");
				int totalOfRecovered = rs.getInt("totalOfRecovered");
				int totalOfDeaths = rs.getInt("totalOfDeaths");
				int updatedInfected = rs.getInt("updatedInfected");
				int updatedRecovered = rs.getInt("udpatedRecovered");
				int updatedDeaths = rs.getInt("updatedDeaths");
				c = new City(id, name, totalOfInfected, totalOfRecovered, totalOfDeaths, updatedInfected, updatedRecovered, updatedDeaths);
				
			}
		} catch(SQLException e) {
			DatabaseConnection.printSQLException(e);
		} finally {
			if(ps != null) {
				try {
					ps.close();
				} catch(SQLException e) {}
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {}
			}
			}
		}
		return c;
	
	} 
}
