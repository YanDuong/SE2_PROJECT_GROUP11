package ServiceTest;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.se2project.covidtracker.model.Admin;
import service.AdminService;

class UserTest {
	private Admin expectedUser1;
	private Admin expectedUser2;
	private Admin expectedUser3;
	private Admin actualUser1;
	private Admin actualUser2;
	private Admin actualUser3;
	private AdminService service;
	@BeforeEach
	void setUp() throws Exception {
		service = new AdminService();
	}

	@Test
	void testLogin() throws SQLException, ClassNotFoundException {
		expectedUser1 = new Admin(1, "Tran Van Khang", "vankhang@gmail.com", "123456");
		expectedUser2 = new Admin(2, "Do Hong Hue", "hue@gmail.com", "123456");
		expectedUser3 = new Admin(3, "Pham Thi Quynh", "quynh@gmail.com", "123456");
		 actualUser1 = service.checkLogin("vankhang@gmail.com", "123456");
	actualUser2 = service.checkLogin("hue@gmail.com", "123456");
	actualUser3 = service.checkLogin("quynh@gmai.com", "123456");
	//test admin 1
	 assertEquals(expectedUser1.getAdmin_id(), actualUser1.getAdmin_id());
	assertEquals(expectedUser1.getName(), actualUser1.getName());
	assertEquals(expectedUser1.getEmail(), actualUser1.getEmail());
	assertEquals(expectedUser1.getPassword(), actualUser1.getPassword());
	//test admin 2
		 assertEquals(expectedUser2.getAdmin_id(), actualUser2.getAdmin_id());
	assertEquals(expectedUser2.getName(), actualUser2.getName());
	assertEquals(expectedUser2.getEmail(), actualUser2.getEmail());
	assertEquals(expectedUser2.getPassword(), actualUser2.getPassword());
	//test admin 3
	assertEquals(expectedUser3.getAdmin_id(), actualUser3.getAdmin_id());
	assertEquals(expectedUser3.getName(), actualUser3.getName());
	assertEquals(expectedUser3.getEmail(), actualUser3.getEmail());
	assertEquals(expectedUser3.getPassword(), actualUser3.getPassword());
	}

}
