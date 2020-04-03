package dao;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import model.Country;

import java.sql.ResultSet;
public class CountryCrudImp implements CountryCrud {

@Override
public void addCountry(Country c) {
	Connection conn = DatabaseConnection.getConnection();
	String sql = "insert into countries values(?,?,?,?,?,?,?,?)";
	PreparedStatement ps = null;
	try {
		 ps = conn.prepareStatement(sql);
		ps.setInt(1,  c.getId());
		ps.setString(2, c.getName());
		ps.setInt(3, c.getTotalOfInfected());
		ps.setInt(4, c.getTotalOfRecovered());
		ps.setInt(5, c.getTotalOfDeaths());
		ps.setInt(6, c.getUpdatedInfected());
		ps.setInt(7, c.getTotalOfRecovered());
		ps.setInt(8, c.getUpdatedDeaths());
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
	String sql = "delete from countries where country_id = ?";
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
	String sql = "update countries set name = ?, totalOfInfected = ?, totalOfRecovered = ?, totalOfDeaths = ?, updatedInfected = ?,updatedRecovered = ?, updatedDeaths = ?;";
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
			int country_id = rs.getInt(1);
			String name = rs.getString(2);
			int totalOfInfected = rs.getInt(3);
			int totalOfRecovered = rs.getInt(4);
			int totalOfDeaths = rs.getInt(5);
			int updatedInfected = rs.getInt(6);
			int updatedRecovered = rs.getInt(7);
			int updatedDeaths = rs.getInt(8);			
			countries.add(new Country(country_id, name, totalOfInfected, totalOfRecovered, totalOfDeaths, updatedInfected, updatedRecovered, updatedDeaths));
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

public Country getCountry(int countryId) {
	Connection conn = DatabaseConnection.getConnection();
	PreparedStatement ps = null;
	Country c = null;
	try {
		ps = conn.prepareStatement("select * from countries where country_id = ?");
		ps.setInt(1,  countryId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			String name = rs.getString("name");
			int totalOfInfected = rs.getInt("totalOfInfected");
			int totalOfRecovered = rs.getInt("totalOfRecovered");
			int totalOfDeaths = rs.getInt("totalOfDeaths");
			int updatedInfected = rs.getInt("updatednfected");
			int updatedRecovered = rs.getInt("updatedRecovered");
			int updatedDeaths = rs.getInt("updatedDeaths");
			 c = new Country(countryId, name, totalOfInfected, totalOfRecovered, totalOfDeaths, updatedInfected, updatedRecovered, updatedDeaths);
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

	
	


}
