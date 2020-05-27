package service;

import daoi.DAOFactory;
import daoi.NewsDAOI;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import net.se2project.covidtracker.model.*;
import java.util.List;


public class NewsService implements AutoCloseable{

	public NewsService() throws SQLException {
		
	}

	private NewsDAOI newsDAO = DAOFactory.getNewsDAO();

	public boolean autoUpdateNews()  throws SQLException, IOException, NumberFormatException, ParseException  {
		return newsDAO.autoUpdateNews();
	}

	public List<News> selectAllNews() throws SQLException {
		return newsDAO.selectAllNews();
	}

	public boolean deleteAllNews() throws SQLException {
		return newsDAO.deleteAllNews();
	}

	public boolean resetNewsId() throws SQLException {
		return newsDAO.resetNewsId();
	}

	@Override
	public void close() throws SQLException {

	}
}
