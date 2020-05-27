package daoi;
import net.se2project.covidtracker.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;


public interface ChartDAOI {

	void autoUpdateChart() throws SQLException, IOException, NumberFormatException, ParseException ;
	List<Chart> selectAllChart();
	void resetChartId() throws SQLException;
	List<Chart> selectAllWorldChart();
	void deleteAllChart() throws SQLException;
}
