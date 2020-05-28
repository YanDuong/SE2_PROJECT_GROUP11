package service;

import daoi.DAOFactory;
import daoi.VietnamDAOI;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import net.se2project.covidtracker.model.*;

public class VietnamService implements AutoCloseable{
	public VietnamService() throws SQLException {
		
	}
	private VietnamDAOI vietnamDAOI = DAOFactory.getNewVietnamDAO();

	public boolean autoUpdateVietnam() throws SQLException, IOException, NumberFormatException, ParseException {
		return vietnamDAOI.autoUpdateVietnam();
	}

	public Vietnam selectProvince(int id) throws SQLException {
		return vietnamDAOI.selectProvince(id);
	}

	public boolean deleteAllProvince() throws SQLException{
		return vietnamDAOI.deleteAllProvince();
	}

	public List<Vietnam> listProvinceTotal() throws SQLException {
		return vietnamDAOI.listProvinceTotal();
	}

	public List<Vietnam> selectAllProvince() throws SQLException{
		return vietnamDAOI.selectAllProvince();
	}

	public boolean deleteProvince(int id) throws SQLException{
		return vietnamDAOI.deleteProvince(id);
	}

	public boolean resetProvincesId() throws SQLException{
		return vietnamDAOI.resetProvincesId();
	}

	public boolean insertProvince(Vietnam v) throws SQLException {
		return vietnamDAOI.insertProvince(v);
	}

	public boolean updateProvince(Vietnam v) throws SQLException {
		return vietnamDAOI.updateProvince(v);
	}

	@Override
	public void close() {

	}
}
