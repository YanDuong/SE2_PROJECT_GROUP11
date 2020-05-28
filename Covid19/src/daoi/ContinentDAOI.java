package daoi;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import net.se2project.covidtracker.model.Continent;


public interface ContinentDAOI {

	boolean autoUpdateContinent() throws SQLException,IOException, NumberFormatException, ParseException;
	Continent selectContinent(int id) throws SQLException, ParseException;
	List<Continent> selectAllContinent()  throws SQLException, ParseException;
	boolean deleteContinent(int id)  throws SQLException, ParseException;
	boolean deleteAllContinent()  throws SQLException, ParseException;
	boolean resetContinentId()  throws SQLException, ParseException;
	boolean updateContinent(Continent continent)  throws SQLException, ParseException;
	boolean insertContinent(Continent continent)  throws SQLException, ParseException;
}
