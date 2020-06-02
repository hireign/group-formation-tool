package daoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dal.twentythree.gft.dao.UserContactInfo;

@SpringBootTest(classes=UserContactInfoTest.class)
public class UserContactInfoTest {
	
	@Test
	public void setBannerId() {
		UserContactInfo user = new UserContactInfo();
		user.setBannerId("B00835826");
		assertEquals("B00835826", user.getBannerId());
	}
	
	@Test
	public void getBannerId() {
		UserContactInfo user = new UserContactInfo();
		user.setBannerId("B00835825");
		assertEquals("B00835825", user.getBannerId());
	}
	
	@Test
	public void setEmailId() {
		UserContactInfo user = new UserContactInfo();
		user.setEmailId("shewanirahul@dal.ca");
		assertEquals("shewanirahul@dal.ca", user.getEmailId());
	}
	
	@Test
	public void getEmailId() {
		UserContactInfo user = new UserContactInfo();
		user.setEmailId("shewanirahul@dal.ca");
		assertEquals("shewanirahul@dal.ca", user.getEmailId());
	}
	
	@Test
	public void setFirstName() {
		UserContactInfo user = new UserContactInfo();
		user.setFirstName("Rahul");
		assertEquals("Rahul", user.getFirstName());
	}
	
	@Test
	public void getFirstName() {
		UserContactInfo user = new UserContactInfo();
		user.setFirstName("Rahul");
		assertEquals("Rahul", user.getFirstName());
	}
	
	@Test
	public void setLastName() {
		UserContactInfo user = new UserContactInfo();
		user.setLastName("Shewani");
		assertEquals("Shewani", user.getLastName());
	}

	@Test
	public void getLastName() {
		UserContactInfo user = new UserContactInfo();
		user.setLastName("Shewani");
		assertEquals("Shewani", user.getLastName());
	}
	
	@Test
	public void setPassword() {
		UserContactInfo user = new UserContactInfo();
		user.setPassword("qwertyui");
		assertEquals("qwertyui", user.getPassword());
	}
	
	@Test
	public void getPassword() {
		UserContactInfo user = new UserContactInfo();
		user.setPassword("qwertyui");
		assertEquals("qwertyui", user.getPassword());
	}
	
}
