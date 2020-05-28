package daoi;
import net.se2project.covidtracker.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;


public interface CountryDAOI {

    boolean autoUpdateCountry() throws SQLException, IOException, NumberFormatException, ParseException ;
    Country selectCountry(int id) throws SQLException;
    boolean deleteCountry(int id) throws SQLException;
    List<Country> selectAllCountries() throws SQLException;
    boolean updateCountry(Country c) throws SQLException;
    boolean deleteAllCountry() throws SQLException;
    Country findTotal(int id) throws SQLException;
    boolean insertCountry(Country c) throws SQLException;
    boolean resetCountriesId() throws SQLException;
}
