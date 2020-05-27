package service;

import daoi.DAOFactory;
import daoi.ChartDAOI;
import java.text.ParseException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import net.se2project.covidtracker.model.*;


public class ChartService implements AutoCloseable {
	public ChartService() throws SQLException {
		
	}
	private ChartDAOI chartDAO = DAOFactory.getChartDAO();

	public void autoUpdateChart() throws SQLException, ParseException, IOException {
		chartDAO.autoUpdateChart();
	}

	public void deleteAllChart() throws SQLException {
		chartDAO.deleteAllChart();
	}

	public void resetChartId() throws SQLException{
		chartDAO.resetChartId();
	}

	public List<Chart> selectAllChart() {
		return chartDAO.selectAllChart();
	}

	public List<Chart> selectAllWorldChart() {
		return chartDAO.selectAllWorldChart();
	}

	@Override
	public void close() throws SQLException {

	}
}
