package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.AccessControl.*;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
@SuppressWarnings("deprecation")
public class UserTest
{
	@Test
	public void ConstructorTests() throws Exception
	{
		IUser u = new User();
		Assert.isTrue(u.getBannerID().isEmpty());
		Assert.isTrue(u.getFirstName().isEmpty());
		Assert.isTrue(u.getLastName().isEmpty());
		Assert.isTrue(u.getEmail().isEmpty());
		
		IUserPersistence userDBMock = new UserDBMock();
		u = new User(1, userDBMock);
		Assert.isTrue(u.getBannerID().equals("B00000000"));
		
		u = new User("B00000000", userDBMock);
		Assert.isTrue(u.getBannerID().equals("B00000000"));
	}
	
	@Test
	public void setIDTest()
	{
		IUser u = new User();
		u.setID(10);
		Assert.isTrue(10 == u.getID());
	}
	
	@Test
	public void getIDTest()
	{
		IUser u = new User();
		u.setID(10);
		Assert.isTrue(10 == u.getID());
	}
	
	@Test
	public void setBannerIDTest()
	{
		IUser u = new User();
		u.setBannerID("B00000000");
		Assert.isTrue(u.getBannerID().equals("B00000000"));
	}
	
	@Test
	public void getBannerIDTest()
	{
		IUser u = new User();
		u.setBannerID("B00000000");
		Assert.isTrue(u.getBannerID().equals("B00000000"));
	}
	
	@Test
	public void setFirstNameTest()
	{
		IUser u = new User();
		u.setFirstName("Rob");
		Assert.isTrue(u.getFirstName().equals("Rob"));
	}
	
	@Test
	public void getFirstNameTest()
	{
		IUser u = new User();
		u.setFirstName("Rob");
		Assert.isTrue(u.getFirstName().equals("Rob"));
	}

	@Test
	public void setLastNameTest()
	{
		IUser u = new User();
		u.setLastName("Hawkey");
		Assert.isTrue(u.getLastName().equals("Hawkey"));
	}

	@Test
	public void getLastNameTest()
	{
		IUser u = new User();
		u.setLastName("Hawkey");
		Assert.isTrue(u.getLastName().equals("Hawkey"));
	}
	
	@Test
	public void setEmailTest()
	{
		IUser u = new User();
		u.setEmail("rhawkey@dal.ca");
		Assert.isTrue(u.getEmail().equals("rhawkey@dal.ca"));
	}
	
	@Test
	public void getEmailTest()
	{
		IUser u = new User();
		u.setEmail("rhawkey@dal.ca");
		Assert.isTrue(u.getEmail().equals("rhawkey@dal.ca"));
	}
	
	@Test
	public void createUser() throws Exception
	{		
		IUserPersistence userDB = new UserDBMock();
		IUser user = new User();
		userDB.createUser(user);
		Assert.isTrue(user.getId() == 0);
		Assert.isTrue(user.getBannerID().equals("B00000000"));
	}

	@Test
	public void isBannerIDValidTest()
	{
		Assert.isTrue(User.isBannerIDValid("B000000000"));
		assertThat(User.isBannerIDValid(null)).isFalse();
		assertThat(User.isBannerIDValid("")).isFalse();
	}
		
	@Test
	public void isFirstNameValidTest()
	{
		Assert.isTrue(User.isFirstNameValid("rob"));
		assertThat(User.isFirstNameValid(null)).isFalse();
		assertThat(User.isFirstNameValid("")).isFalse();
	}
	
	@Test
	public void isLastNameValidTest()
	{
		Assert.isTrue(User.isLastNameValid("hawkey"));
		assertThat(User.isLastNameValid(null)).isFalse();
		assertThat(User.isLastNameValid("")).isFalse();
	}
	
	@Test
	public void isEmailValidTest()
	{
		Assert.isTrue(User.isEmailValid("rhawkey@dal.ca"));
		assertThat(User.isEmailValid(null)).isFalse();
		assertThat(User.isEmailValid("")).isFalse();
		assertThat(User.isEmailValid("@dal.ca")).isFalse();
		assertThat(User.isEmailValid("rhawkey@")).isFalse();
	}	
}
