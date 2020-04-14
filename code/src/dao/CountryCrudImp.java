package dao;
import java.util.List;

import java.util.Map;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Connection;
import model.Country;

import java.sql.ResultSet;
public class CountryCrudImp implements CountryCrud {

@Override
public void addCountry(Country c) {
	Connection conn = DatabaseConnection.getConnection();
	String sql = "insert into countries (name, totalOfInfected, totalOfRecovered, totalOfDeaths, updatedInfected, updatedRecovered, updatedDeaths) values (?,?,?,?,?,?,?)";
	PreparedStatement ps = null;
	try {
		 ps = conn.prepareStatement(sql);
		
		ps.setString(1, c.getName());
		ps.setInt(2, c.getTotalOfInfected());
		ps.setInt(3, c.getTotalOfRecovered());
		ps.setInt(4, c.getTotalOfDeaths());
		ps.setInt(5, c.getUpdatedInfected());
		ps.setInt(6, c.getTotalOfRecovered());
		ps.setInt(7, c.getUpdatedDeaths());
		ps.executeUpdate();
		conn.close();
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
}

@Override
public boolean deleteCountry(int countryId) {
	boolean isDeleted = false;
	Connection conn = DatabaseConnection.getConnection();
	String sql = "delete from countries where countryId = ?";
	PreparedStatement ps = null;
	try {
		 ps = conn.prepareStatement(sql);
		ps.setInt(1, countryId);
		int row = ps.executeUpdate();
		isDeleted =  row > 0;
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
public boolean updateCountry(Country c){
	boolean isUpdated = false;
	Connection conn = DatabaseConnection.getConnection();
	PreparedStatement ps = null;
	String sql = "update countries set name = ?, totalOfInfected = ?, totalOfRecovered = ?, totalOfDeaths = ?, updatedInfected = ?,updatedRecovered = ?, updatedDeaths = ? where countryId = ?;";
	try {
		ps = conn.prepareStatement(sql);
		ps.setString(1, c.getName());
		ps.setInt(2, c.getTotalOfInfected());
		ps.setInt(3, c.getTotalOfRecovered());
		ps.setInt(4, c.getTotalOfDeaths());
		ps.setInt(5, c.getUpdatedInfected());
		ps.setInt(6, c.getUpdatedRecovered());
		ps.setInt(7, c.getUpdatedDeaths());
		ps.setInt(8, c.getId());
		int row = ps.executeUpdate();
		isUpdated = row > 0;
		conn.close();
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
	return isUpdated;
}

@Override
public List<Country> getAllCountries() {
	List<Country> countries = new ArrayList<>();
	Connection conn = DatabaseConnection.getConnection();
	String sql = "select * from countries";
	PreparedStatement ps = null;
	try { 
		
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			int id = rs.getInt("countryId");
			String name = rs.getString("name");
			int totalOfInfected = rs.getInt("totalOfInfected");
			int totalOfRecovered = rs.getInt("totalOfRecovered");
			int totalOfDeaths = rs.getInt("totalOfDeaths");
			int updatedInfected = rs.getInt("updatedInfected");
			int updatedRecovered = rs.getInt("updatedRecovered");
			int updatedDeaths = rs.getInt("updatedDeaths");			
			countries.add(new Country(id, name, totalOfInfected, totalOfRecovered, totalOfDeaths, updatedInfected, updatedRecovered, updatedDeaths));
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
	return countries;
}

@Override
public Country getCountry(int id) {
	Connection conn = DatabaseConnection.getConnection();
	PreparedStatement ps = null;
	Country c = null;
	try {
		ps = conn.prepareStatement("select * from countries where countryId = ?");
		ps.setInt(1,  id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			String name = rs.getString("name");
			int totalOfInfected = rs.getInt("totalOfInfected");
			int totalOfRecovered = rs.getInt("totalOfRecovered");
			int totalOfDeaths = rs.getInt("totalOfDeaths");
			int updatedInfected = rs.getInt("updatedInfected");
			int updatedRecovered = rs.getInt("updatedRecovered");
			int updatedDeaths = rs.getInt("updatedDeaths");
			 c = new Country(id, name, totalOfInfected, totalOfRecovered, totalOfDeaths, updatedInfected, updatedRecovered, updatedDeaths);
		}
		
	} catch(SQLException e) {
		DatabaseConnection.printSQLException(e);
	} finally {
		if(ps != null) {
			try {
			ps.close();
			} catch(SQLException e){}
		}
		
		if(conn != null) {
			try {
				conn.close();
			} catch(SQLException e) {}
		}
	}
	return c;
}

	
	@SuppressWarnings("null")
	public List<Map<Object, Object>> getCountryData() {
		Map<Object, Object> map = null;
		List<Object> list = null;
		List<Map<Object, Object>> charts = new ArrayList<Map<Object, Object>>();
		Connection conn = DatabaseConnection.getConnection();
		String sql = "select * from countries";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				int totalOfInfected = Integer.parseInt(rs.getString("totalOfInfected"));
				int totalOfRecovered = Integer.parseInt(rs.getString("totalOfRecovered"));
				int totalOfDeaths = Integer.parseInt(rs.getString("totalOfDeaths"));
				int updatedInfected = Integer.parseInt(rs.getString("updatedInfected"));
				int updatedRecovered = Integer.parseInt(rs.getString("updatedRecovered"));
				int updatedDeaths = Integer.parseInt(rs.getString("updatedDeaths"));
				list = Arrays.asList(totalOfInfected, totalOfRecovered, totalOfDeaths, updatedInfected, updatedRecovered, updatedDeaths);
				map.put("Name", name);
				map.put("y", list);
				charts.add(map);
			}
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
		return charts;
	}
	public boolean checkInteger(String s1, String s2, String s3, String s4, String s5, String s6) {
		try {
			Integer.parseInt(s1);
			Integer.parseInt(s2);
			Integer.parseInt(s3);
			Integer.parseInt(s4);
			Integer.parseInt(s5);
			Integer.parseInt(s6);
			return true;
			
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
	public boolean checkExistingField(String name) {
		name.trim();
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("select * from countries where name = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			} else 
				return false;
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

	/*
	 * public boolean resetId() { Connection conn =
	 * DatabaseConnection.getConnection(); PreparedStatement ps = null; try { ps =
	 * conn.prepareStatement("alter table countries auto_increment = 1"); ResultSet
	 * rs = ps.executeQuery(); while(rs.next()) { return true;
	 * 
	 * }
	 * 
	 * }catch(SQLException e) { DatabaseConnection.printSQLException(e); } finally {
	 * if(ps != null) { try { ps.close(); } catch(SQLException e) {} } if(conn !=
	 * null) { try { conn.close(); } catch(SQLException e) {} } } return false; }
	 */
	
	



