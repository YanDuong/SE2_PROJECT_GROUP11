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

	public List<Vietnam> selectAllProvince() throws SQLException{
		return countryDAOI.selectAllProvince();
	}

	public boolean autoUpdateVietnam() throws SQLException, IOException, NumberFormatException, ParseException {
		return countryDAOI.autoUpdateVietnam();
	}

	public Country selectCountry(int id) throws SQLException {
		return countryDAOI.selectCountry(id);
	}

	public List<Country> listTotal() throws SQLException{
		return countryDAOI.listTotal();
	}

	public List<Country> listTotalProvince() throws SQLException{
		return countryDAOI.listTotalProvince();
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
