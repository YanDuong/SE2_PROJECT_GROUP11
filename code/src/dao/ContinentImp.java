package dao;
import model.Continent;


import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class ContinentImp implements ContinentCrud {
	public boolean updateContinent(Continent c) {
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement ps = null;
		boolean isUpdated = false;
		try {
			ps = conn.prepareStatement("update table Continent set name = ?, totalOfInfected = ?, totalOfRecovered = ?, totalOfDeaths = ?, updatedInfected = ?, udpatedRecovered = ?, updatedDeaths = ? where id = ?");
			ps.setString(1,  c.getName());
			ps.setInt(2, c.getTotalOfInfected());
			ps.setInt(3, c.getTotalOfRecovered());
			ps.setInt(4, c.getTotalOfDeaths());
			ps.setInt(5, c.getUpdatedInfected());
			ps.setInt(6, c.getUpdatedRecovered());
			ps.setInt(7, c.getUpdatedDeaths());
			ps.setInt(8, c.getId());
			int row = ps.executeUpdate();
			isUpdated = row > 0;
		} catch(SQLException e) {
			DatabaseConnection.getConnection();
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
	
	@Override
	public List<Continent> getAllContinents() {
		List<Continent> continents = new ArrayList<>();
		Connection conn = DatabaseConnection.getConnection();
		String sql = "select * from continent";
		PreparedStatement ps = null;
		try { 
			
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int totalOfInfected = rs.getInt("totalOfInfected");
				int totalOfRecovered = rs.getInt("totalOfRecovered");
				int totalOfDeaths = rs.getInt("totalOfDeaths");
				int updatedInfected = rs.getInt("updatedInfected");
				int updatedRecovered = rs.getInt("updatedRecovered");
				int updatedDeaths = rs.getInt("updatedDeaths");			
				continents.add(new Continent(id, name, totalOfInfected, totalOfRecovered, totalOfDeaths, updatedInfected, updatedRecovered, updatedDeaths));
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
		return continents;
	}
	@Override
	public Continent getContinent(int id) {
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement ps = null;
		Continent c = null;
		try {
			
			ps = conn.prepareStatement("select * from continent where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				int totalOfInfected = rs.getInt("totalOfInfected");
				int totalOfRecovered = rs.getInt("totalOfRecovered");
				int totalOfDeaths = rs.getInt("totalOfDeaths");
				int newInfected = rs.getInt("updatedInfected");
				int newRecovered = rs.getInt("updatedRecovered");
				int newDeaths = rs.getInt("updatedDeaths");
				 c = new Continent(id, name, totalOfInfected, totalOfRecovered, totalOfDeaths, newInfected, newRecovered, newDeaths);
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
		return c;
	}
	
	
	
	
	

	@Override
	public int getTotalOfInfected() {
		int sum = 0;
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("select sum(totalOfInfected) from continent");
			ResultSet rs = ps.executeQuery();
			rs.next();
			sum = rs.getInt(1);
		} catch(SQLException e) {
			DatabaseConnection.printSQLException(e);
		}finally {
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
		
		return sum;
	}
	
	@Override
	public int getTotalOfRecovered() {
		int sum = 0;
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("select sum(totalOfRecovered) from continent");
			ResultSet rs = ps.executeQuery();
			rs.next();
			sum = rs.getInt(1);
		} catch(SQLException e) {
			DatabaseConnection.printSQLException(e);
		}finally {
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
		
		return sum;
	}
	
	@Override
	public int getTotalOfDeaths() {
		int sum = 0;
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("select sum(totalOfDeaths) from continent");
			ResultSet rs = ps.executeQuery();
			rs.next();
			sum = rs.getInt(1);
		} catch(SQLException e) {
			DatabaseConnection.printSQLException(e);
		}finally {
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
		
		return sum;
	}
	
	
	@Override
	public int getUpdatedInfected() {
		int sum = 0;
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("select sum(updatedInfected) from continent");
			ResultSet rs = ps.executeQuery();
			rs.next();
			sum = rs.getInt(1);
		} catch(SQLException e) {
			DatabaseConnection.printSQLException(e);
		}finally {
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
		
		return sum;
	}
	
	@Override
	public int getUpdatedRecovered() {
		int sum = 0;
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("select sum(updatedRecovered) from continent");
			ResultSet rs = ps.executeQuery();
			rs.next();
			sum = rs.getInt(1);
		} catch(SQLException e) {
			DatabaseConnection.printSQLException(e);
		}finally {
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
		
		return sum;
	}
	
	@Override
	public int getUpdatedDeaths() {
		int sum = 0;
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("select sum(updatedDeaths) from continent");
			ResultSet rs = ps.executeQuery();
			rs.next();
			sum = rs.getInt(1);
		} catch(SQLException e) {
			DatabaseConnection.printSQLException(e);
		}finally {
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
		
		return sum;
	}
	
}




