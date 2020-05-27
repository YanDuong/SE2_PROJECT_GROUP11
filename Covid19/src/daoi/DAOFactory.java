package daoi;
import net.se2project.covidtracker.dao.*;
import java.sql.SQLException;


public class DAOFactory {

	public static CountryDAOI getNewCountryDAO() throws SQLException {
		return new CountryDAO();
	}

	public static ContinentDAOI getNewContinentDAO() throws SQLException  {
		return new ContinentDao();
	}

	public static VietnamDAOI getNewVietnamDAO() throws SQLException{
		return new VietnamDAO();
	}

	public static NewsDAOI getNewsDAO() throws SQLException{
		return new NewsDAO();
	}

	public static ChartDAOI getChartDAO() throws SQLException {
		return new ChartDao();
	}
}
