package ServiceTest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.se2project.covidtracker.model.Country;
import service.CountryService;

class CountryTest {
		private CountryService service;
		private Country expectedCountry;
		private Country actualCountry;
		private Country newCountry;
		private Country updatedCountry;
		private List<Country> expectedCountryList;
		private List<Country> actualCountryList;
		@BeforeEach
		void setUp() throws Exception {
			service = new CountryService();
			expectedCountry = new Country(1, "American", 10000, 1000, 100, 100, 100, 100, 100);
			actualCountry = service.selectCountry(1);

			newCountry = new Country(5, "Zanrakd", 10000, 1000, 100, 100, 100, 100, 100);
		}

		@Test
		void testAutoUpdateCountry() throws SQLException, NumberFormatException, IOException, ParseException {
			/* assertEquals(true, service.autoUpdateCountry()); */
		}
		@Test
		void testSelectedCountry() throws SQLException {

			assertEquals(expectedCountry.getId(), actualCountry.getId());
			assertEquals(expectedCountry.getTotal_cases(), actualCountry.getTotal_cases());
			assertEquals(expectedCountry.getTotal_death(), actualCountry.getTotal_death());
			assertEquals(expectedCountry.getTotal_recovered(), actualCountry.getTotal_recovered());
			assertEquals(expectedCountry.getNew_cases(), actualCountry.getNew_cases());
			assertEquals(expectedCountry.getNew_death(), actualCountry.getNew_death());
			assertEquals(expectedCountry.getActive_cases(), actualCountry.getActive_cases());
			assertEquals(expectedCountry.getCritical_cases(), actualCountry.getCritical_cases());
			assertEquals(null, service.selectCountry(10));

		}

		@Test
		void testInsertCountry() throws SQLException {

			/*
			 * // assertEquals(true, service.insertCountry(newCountry));
			 */
			/* assertEquals(false, service.insertCountry(newCountry)); */

		}

		@Test
		void testDeleteCountry() throws SQLException {


			/* assertEquals(true, service.deleteCountry(4)); */

			assertEquals(false, service.deleteCountry(20));
		}

		/*
		 * @Test void resetCountriesId() throws SQLException { assertEquals(true,
		 * service.resetCountriesId()); }
		 */

	@Test
	void testUpdateCountry() throws SQLException {
		Country updatedCountry = new Country(3, "Japan", 110, 10, 10, 10, 10,10, 10 );
		Country notInDatabaseCountry = new Country(21, "Ynaa", 10, 10, 10, 10, 10, 10, 10 );
		assertEquals(true, service.updateCountry(updatedCountry));

		assertEquals(false, service.updateCountry(notInDatabaseCountry));
	}


	@Test
	void testSelectAllCountries() throws SQLException {
		expectedCountryList = new ArrayList<>();
		expectedCountryList.add(new Country(1, "American", 10000, 1000, 100, 100, 100, 100, 100));
		expectedCountryList.add(new Country(2, "England", 100, 100, 100, 100, 100, 100, 100));
		expectedCountryList.add(new Country(3, "Japan", 110, 10, 10, 10, 10, 10, 10));
		actualCountryList = service.selectAllCountries();
		for(int i = 0; i < expectedCountryList.size(); i++) {
			assertEquals(expectedCountryList.get(i).getId(), actualCountryList.get(i).getId());
			assertEquals(expectedCountryList.get(i).getCountry_name(), actualCountryList.get(i).getCountry_name());
			assertEquals(expectedCountryList.get(i).getTotal_cases(), actualCountryList.get(i).getTotal_cases());
			assertEquals(expectedCountryList.get(i).getTotal_death(), actualCountryList.get(i).getTotal_death());
			assertEquals(expectedCountryList.get(i).getTotal_recovered(), actualCountryList.get(i).getTotal_recovered());
			assertEquals(expectedCountryList.get(i).getNew_cases(), actualCountryList.get(i).getNew_cases());
			assertEquals(expectedCountryList.get(i).getNew_death(), actualCountryList.get(i).getNew_death());
			assertEquals(expectedCountryList.get(i).getActive_cases(), actualCountryList.get(i).getActive_cases());
			assertEquals(expectedCountryList.get(i).getCritical_cases(), actualCountryList.get(i).getCritical_cases());
			assertEquals(expectedCountryList.size(), actualCountryList.size());
		}
	}
	@Test
	void testDeleteAllCountry() throws SQLException {
			/* assertEquals(true, service.deleteAllCountry()); */


	}
	@Test
	void testResetCountryId() throws SQLException {
		 assertEquals(true, service.resetCountriesId());
	}

}
