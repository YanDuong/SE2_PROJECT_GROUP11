package ServiceTest;


import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import net.se2project.covidtracker.model.*;
import service.ChartService;

class ChartTest {
private List<Chart> VietnamChartList;
private List<Chart> actualChartList;
private ChartService service;
private List<Chart> WorldChartList;
private List<Chart> WorldDatabaseChartList;
	@BeforeEach
	void setUp() throws Exception {
		service = new ChartService();
		
	}
@Test
void testAutoUpdateChart() throws IOException, SQLException, ParseException {
		/* assertEquals(true, service.autoUpdateChart()); */
}
	
	@Test
	void testSelectAllChart() {
		VietnamChartList = new ArrayList<>();
		VietnamChartList.add(new Chart(847, "Vietnam", "Dec31", "2019", 0));
		VietnamChartList.add(new Chart(848, "Vietnam", "Jan1", "2020", 0));
		VietnamChartList.add(new Chart(849, "Vietnam", "Jan2", "2020", 0));
		actualChartList = service.selectAllChart();
		for(int i = 0; i < VietnamChartList.size(); i++) {
			assertEquals(VietnamChartList.get(i).getName(), actualChartList.get(i).getName());
			assertEquals(VietnamChartList.get(i).getCases(), actualChartList.get(i).getCases());
			assertEquals(VietnamChartList.get(i).getDate(), actualChartList.get(i).getDate());
			assertEquals(VietnamChartList.get(i).getId(), actualChartList.get(i).getId());
			assertEquals(VietnamChartList.get(i).getYear(), actualChartList.get(i).getYear());
			
			
		}
	}
	 @Test
	void testDeleteAllChart() throws SQLException {
		/*
		 * service.deleteAllChart(); List<Chart> currentChartList =
		 * service.selectAllChart(); assertEquals(0, currentChartList.size());
		 */
	 }
	 
	 @Test
	 void testSelectAllWorldChart() throws SQLException {
		 WorldChartList = new ArrayList<>();
		 WorldChartList.add(new Chart(984,	"World", "Dec31", "2019",27));
		 WorldChartList.add(new Chart(985,	"World", "Jan1", "2020",0));
		 WorldChartList.add(new Chart(986,	"World", "Jan2", "2020",0));
		 WorldDatabaseChartList = service.selectAllWorldChart();
		 for(int i = 0; i < WorldChartList.size(); i++) {
			 assertEquals(WorldChartList.get(i).getId(), WorldDatabaseChartList.get(i).getId());
			 assertEquals(WorldChartList.get(i).getName(), WorldDatabaseChartList.get(i).getName());
			 assertEquals(WorldChartList.get(i).getYear(), WorldDatabaseChartList.get(i).getYear());
			 assertEquals(WorldChartList.get(i).getDate(), WorldDatabaseChartList.get(i).getDate());
			 assertEquals(WorldChartList.get(i).getCases(), WorldDatabaseChartList.get(i).getCases());
			 
		 }
	 }
	 @Test
	 void testResetChartId() throws SQLException {
		 assertEquals(true, service.resetChartId());
	 }

	
}

