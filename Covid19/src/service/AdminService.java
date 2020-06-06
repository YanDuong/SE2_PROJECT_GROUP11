package service;

import java.sql.SQLException;

import daoi.DAOFactory;
import daoi.UserDAOI;
import net.se2project.covidtracker.model.Admin;

public class AdminService {
	public AdminService() throws SQLException {
		
	}
private UserDAOI userDAOI = DAOFactory.getUserDAO();

public Admin checkLogin(String email, String password) throws SQLException, ClassNotFoundException {
	return userDAOI.checkLogin(email, password);
}
}
