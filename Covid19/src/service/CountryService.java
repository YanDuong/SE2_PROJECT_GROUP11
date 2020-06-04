package service;

import daoi.DAOFactory;
import daoi.CountryDAOI;
import net.se2project.covidtracker.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;


public class CountryService implements AutoCloseable {
	
	public CountryService() throws SQLException {
		
	}
	private CountryDAOI countryDAOI = DAOFactory.getNewCountryDAO();

	public boolean autoUpdateCountry() throws SQLException, IOException, NumberFormatException, ParseException{
	return countryDAOI.autoUpdateCountry();
	}

	public Country selectCountry(int id) throws SQLException {
		return countryDAOI.selectCountry(id);
	}

	public boolean deleteCountry(int id) throws SQLException {
		return countryDAOI.deleteCountry(id);
	}

	public List<Country> selectAllCountries() throws SQLException{
		return countryDAOI.selectAllCountries();
	}

	public boolean updateCountry(Country c) throws SQLException{
		return countryDAOI.updateCountry(c);
	}

	public boolean deleteAllCountry() throws SQLException {
		return countryDAOI.deleteAllCountry();
	}
	public boolean resetCountriesId() throws SQLException {
		return countryDAOI.resetCountriesId();
	}

	public Country findTotal(int id) throws SQLException {
		return countryDAOI.findTotal(id);
	}

	public boolean insertCountry(Country c) throws SQLException{
		return countryDAOI.insertCountry(c);
	}

	@Override
	public void close() throws SQLException {

	}
}
