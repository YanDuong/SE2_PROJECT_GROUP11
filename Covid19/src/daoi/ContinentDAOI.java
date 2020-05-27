package daoi;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import net.se2project.covidtracker.model.*;


public interface ContinentDAOI {

	boolean autoUpdateContinent() throws SQLException,IOException, NumberFormatException, ParseException;
	List<Vietnam> selectAllProvince() throws SQLException, ParseException;
	boolean autoUpdateVietnam() throws SQLException, IOException, NumberFormatException, ParseException;
	Continent selectContinent(int id) throws SQLException, ParseException;
	List<Continent> listTotal()  throws SQLException, ParseException;
	List<Continent> listTotalProvince()  throws SQLException, ParseException;
	List<Continent> selectAllContinent()  throws SQLException, ParseException;
	boolean deleteContinent(int id)  throws SQLException, ParseException;
	boolean deleteAllContinent()  throws SQLException, ParseException;
	boolean resetContinentId()  throws SQLException, ParseException;
	boolean updateContinent(Continent continent)  throws SQLException, ParseException;
	Continent findTotal(int id)  throws SQLException;
	boolean insertContinent(Continent continent)  throws SQLException, ParseException;
}
