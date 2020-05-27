package service;
import daoi.DAOFactory;


import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import daoi.*;
import net.se2project.covidtracker.model.*;
public class ContinentService implements AutoCloseable{

	public ContinentService() throws SQLException {
		
	}

	private ContinentDAOI continentDaoi = DAOFactory.getNewContinentDAO();

	public boolean insertContinent(Continent c)  throws SQLException, ParseException{
		return continentDaoi.insertContinent(c);
	}

	public boolean autoUpdateContinent() throws SQLException, IOException, NumberFormatException, ParseException {
		return continentDaoi.autoUpdateContinent();
	}

	public boolean autoUpdateVietNam() throws SQLException, IOException, NumberFormatException, ParseException {
		return continentDaoi.autoUpdateVietnam();
	}

	public Continent selectContinent(int id) throws SQLException, ParseException {
		return continentDaoi.selectContinent(id);
	}

	public boolean updateContinent(Continent c) throws SQLException, ParseException  {
		return continentDaoi.updateContinent(c);
	}

	public List<Continent> listTotal() throws SQLException, ParseException{
		return continentDaoi.listTotal();
	}

	public List<Continent> listTotalProvince() throws SQLException, ParseException {
		return continentDaoi.listTotalProvince();
	}

	public List<Vietnam> selectAllProvince() throws SQLException, ParseException {
		return continentDaoi.selectAllProvince();
	}

	public List<Continent> selectAllContinent() throws SQLException, ParseException {
		return continentDaoi.selectAllContinent();
	}

	public boolean deleteContinent(int id) throws SQLException, ParseException {
		return continentDaoi.deleteContinent(id);
	}

	public boolean deleteAllContinent() throws SQLException, ParseException  {
		return continentDaoi.deleteAllContinent();
	}

	public boolean resetContinentId() throws SQLException, ParseException {
		return continentDaoi.resetContinentId();
	}

	public Continent findTotal(int id) throws SQLException, ParseException {
		return continentDaoi.findTotal(id);
	}

	@Override
	public void close() throws SQLException {

	}

 }
