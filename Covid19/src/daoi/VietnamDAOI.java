package daoi;
import net.se2project.covidtracker.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;


public interface VietnamDAOI {

    boolean autoUpdateVietnam() throws SQLException, IOException, NumberFormatException, ParseException;
    Vietnam selectProvince(int id) throws SQLException;
    boolean deleteAllProvince() throws SQLException;
    List<Vietnam> listProvinceTotal() throws SQLException;
    List<Vietnam> selectAllProvince() throws SQLException;
    boolean deleteProvince(int id) throws SQLException;
    boolean resetProvincesId() throws SQLException;
    boolean insertProvince(Vietnam v) throws SQLException;
    boolean updateProvince(Vietnam v) throws SQLException;
}
