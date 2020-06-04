package daoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dao.User;

@SpringBootTest(classes=UserTest.class)
public class UserTest {
	
	@Test
	public void setBannerId() {
		User user = new User();
		user.setBannerId("B00835826");
		assertEquals("B00835826", user.getBannerId());
	}
	
	@Test
	public void getBannerId() {
		User user = new User();
		user.setBannerId("B00835825");
		assertEquals("B00835825", user.getBannerId());
	}
	
	@Test
	public void setEmailId() {
		User user = new User();
		user.setEmailId("shewanirahul@dal.ca");
		assertEquals("shewanirahul@dal.ca", user.getEmailId());
	}
	
	@Test
	public void getEmailId() {
		User user = new User();
		user.setEmailId("shewanirahul@dal.ca");
		assertEquals("shewanirahul@dal.ca", user.getEmailId());
	}
	
	@Test
	public void setFirstName() {
		User user = new User();
		user.setFirstName("Rahul");
		assertEquals("Rahul", user.getFirstName());
	}
	
	@Test
	public void getFirstName() {
		User user = new User();
		user.setFirstName("Rahul");
		assertEquals("Rahul", user.getFirstName());
	}
	
	@Test
	public void setLastName() {
		User user = new User();
		user.setLastName("Shewani");
		assertEquals("Shewani", user.getLastName());
	}

	@Test
	public void getLastName() {
		User user = new User();
		user.setLastName("Shewani");
		assertEquals("Shewani", user.getLastName());
	}
	
	@Test
	public void setPassword() {
		User user = new User();
		user.setPassword("qwertyui");
		assertEquals("qwertyui", user.getPassword());
	}
	
	@Test
	public void getPassword() {
		User user = new User();
		user.setPassword("qwertyui");
		assertEquals("qwertyui", user.getPassword());
	}
	
}
