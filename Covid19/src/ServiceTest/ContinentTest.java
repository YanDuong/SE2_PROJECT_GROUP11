package ServiceTest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.se2project.covidtracker.model.Continent;
import service.ContinentService;

class ContinentTest {
	private ContinentService service;
	private Continent expectedContinent;
	private Continent actualContinent;
	private List<Continent> expectedContinentList;
	private List<Continent> actualContinentList;

	
	@BeforeEach
	void setUp() throws Exception {
		service = new ContinentService();
	}
	

	@Test
	void testSelectContinent() throws SQLException, ParseException{
		expectedContinent = new Continent(1, "North America", 1985234, 4047, 120653, 479, 624697, 2396, 1239884);
		actualContinent = service.selectContinent(1);
		assertEquals(expectedContinent.getCountry_name(), actualContinent.getCountry_name());
		assertEquals(expectedContinent.getActive_cases(), actualContinent.getActive_cases());
		assertEquals(expectedContinent.getCritical_cases(), actualContinent.getCritical_cases());
		assertEquals(expectedContinent.getTotal_cases(), actualContinent.getTotal_cases());
		assertEquals(expectedContinent.getTotal_death(), actualContinent.getTotal_death());
		assertEquals(expectedContinent.getTotal_recovered(), actualContinent.getTotal_recovered());
		assertEquals(expectedContinent.getNew_cases(), actualContinent.getNew_cases());
		assertEquals(expectedContinent.getNew_death(), actualContinent.getNew_death());
	}
	@Test
	void testSelectAllContinent() throws SQLException, ParseException {
		expectedContinentList = new ArrayList<>();
		expectedContinentList.add(new Continent(1, "North America", 1985234, 4047, 120653, 479, 624697, 2396, 1239884));
		expectedContinentList.add(new Continent(2,"South America", 758107 , 619, 36746, 13, 320826, 49, 400535));
		expectedContinentList.add(new Continent(3,"Europe", 1979578, 9679, 171874,	306, 958838, 10342, 848866));
		actualContinentList = service.selectAllContinent();
		for(int i = 0; i < expectedContinentList.size(); i++) {
			assertEquals(expectedContinentList.get(i).getId(), actualContinentList.get(i).getId());
			assertEquals(expectedContinentList.get(i).getCountry_name(), actualContinentList.get(i).getCountry_name());
			assertEquals(expectedContinentList.get(i).getTotal_cases(), actualContinentList.get(i).getTotal_cases());
			assertEquals(expectedContinentList.get(i).getTotal_death(), actualContinentList.get(i).getTotal_death());
			assertEquals(expectedContinentList.get(i).getTotal_recovered(), actualContinentList.get(i).getTotal_recovered());
			assertEquals(expectedContinentList.get(i).getNew_cases(), actualContinentList.get(i).getNew_cases());
			assertEquals(expectedContinentList.get(i).getNew_death(), actualContinentList.get(i).getNew_death());
			assertEquals(expectedContinentList.get(i).getActive_cases(), actualContinentList.get(i).getActive_cases());
			assertEquals(expectedContinentList.get(i).getCritical_cases(), actualContinentList.get(i).getCritical_cases());
		}

	}
	@Test
	void testDeleteContinent() throws SQLException, ParseException {
		/* assertEquals(true, service.deleteContinent(4)); */
		assertEquals(false, service.deleteContinent(20));
	}
	
	@Test
	void testDeleteAllContinent() throws SQLException, ParseException {
		/* assertEquals(true, service.deleteAllContinent()); */
		
	}
	
	@Test
	void updateContinent() throws SQLException, ParseException {
		
		assertEquals(true, service.updateContinent(new Continent(4, "South America", 1000, 100, 100, 100, 100, 100, 100)));
		 
		
		assertEquals(false, service.updateContinent(new Continent(10, "Non-existed continent", 100, 1000, 1000, 1000, 1000, 1000, 1000)));
		
	}
	
	@Test
	void testInsertContinent() throws SQLException, ParseException {
		/*
		 * assertEquals(true, service.insertContinent(new Continent(5, "North America",
		 * 1000000, 1000, 1000, 1000, 1000, 1000, 1000)));
		 */
		assertEquals(false, service.insertContinent(new Continent(4, "South America", 1000, 100, 100, 100, 100, 100, 100)));
	}

@Test
void testAutoUpdateContinent() throws SQLException, ParseException, IOException, ClassNotFoundException {
assertEquals(true, service.autoUpdateContinent());	
}
@Test  
void testResetContinentId() throws SQLException, ParseException {
	 assertEquals(true, service.resetContinentId()); 
}

}
