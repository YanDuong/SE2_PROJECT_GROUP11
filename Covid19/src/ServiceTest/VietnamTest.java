package ServiceTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.se2project.covidtracker.model.Vietnam;
import service.VietnamService;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

class VietnamTest {
	private Vietnam expectedProvince;
	private Vietnam actualProvince;
	private List<Vietnam> expectedProvinceList;
	private List<Vietnam> actualProvinceList;
	private VietnamService service;


	@BeforeEach
	void setUp() throws Exception {
		service = new VietnamService();
	}

	
	@Test
	void testAutoUpdateVietnam() throws SQLException, ParseException, NumberFormatException, IOException {
		/* assertEquals(true, service.autoUpdateVietnam()); */
	}
	@Test
	void testSelectProvince() throws SQLException {
		expectedProvince = new Vietnam(1,"Hà Nội", 114, 1, 113, 0);
		actualProvince =  service.selectProvince(1);
		assertEquals(expectedProvince.getCountry_name(), actualProvince.getCountry_name());
		assertEquals(expectedProvince.getTotal_cases(), actualProvince.getTotal_cases());
		assertEquals(expectedProvince.getTotal_death(), actualProvince.getTotal_death());
		assertEquals(expectedProvince.getTotal_recovered(), actualProvince.getTotal_recovered());

		assertEquals(expectedProvince.getActive_cases(), actualProvince.getActive_cases());
		
		assertEquals(null, service.selectProvince(200));
		
		
	}
	@Test
	void testSelectAllProvince() throws SQLException {
		expectedProvinceList = new ArrayList<>();
		expectedProvinceList.add(new Vietnam(1,"Hà Nội", 114, 1, 113, 0));
		expectedProvinceList.add(new Vietnam(2, "TP. Hồ Chí Minh", 59, 5, 54, 0));
		expectedProvinceList.add(new Vietnam(3,	"Thái Bình", 30, 25, 5,	0));
		actualProvinceList = service.selectAllProvince();
		for(int i = 0; i < expectedProvinceList.size(); i++) {
			assertEquals(expectedProvinceList.get(i).getCountry_name(), actualProvinceList.get(i).getCountry_name());
			assertEquals(expectedProvinceList.get(i).getTotal_cases(), actualProvinceList.get(i).getTotal_cases());
			assertEquals(expectedProvinceList.get(i).getTotal_death(), actualProvinceList.get(i).getTotal_death());
			assertEquals(expectedProvinceList.get(i).getTotal_recovered(), actualProvinceList.get(i).getTotal_recovered());
			assertEquals(expectedProvinceList.get(i).getActive_cases(), actualProvinceList.get(i).getActive_cases());
		}
		
	}
	
	@Test
	void testDeleteProvince() throws SQLException {
		/* assertEquals(true, service.deleteProvince(30)); */
		assertEquals(false, service.deleteProvince(200));
	}
	
	@Test
	void testInsertProvince() throws SQLException {
		/*
		 * assertEquals(true, service.insertProvince(new Vietnam(31, "NewCity", 0, 0, 0,
		 * 0)));
		 */
		assertEquals(false, service.insertProvince(new Vietnam(3, "Thái Bình", 30, 25, 5,	0)));
	}
	
	@Test
	void testUpdateProvince() throws SQLException {
		assertEquals(true, service.updateProvince(new Vietnam(29, "Lai Châu", 0, 0, 0, 0)));
		assertEquals(false, service.updateProvince(new Vietnam(200, "UnknownCity", 0, 0, 0, 0)));
	}
	
	@Test
	 void testResetVietnamId() throws SQLException {
		 assertEquals(true, service.resetProvincesId()); 
	 }

}
