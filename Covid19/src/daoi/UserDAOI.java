package daoi;
import java.sql.SQLException;

import net.se2project.covidtracker.model.*;


public interface UserDAOI {

    Admin checkLogin(String email, String password) throws SQLException,
    ClassNotFoundException;
}
