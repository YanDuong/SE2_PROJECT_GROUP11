package daoi;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import net.se2project.covidtracker.model.*;
import java.util.List;


public interface NewsDAOI {

    boolean autoUpdateNews() throws SQLException, IOException, NumberFormatException, ParseException ;

    List<News> selectAllNews() ;

    boolean deleteAllNews() throws SQLException;

    boolean resetNewsId() throws SQLException;
}
