package CSCI5308.GroupFormationTool.PasswordPolicyTest;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.AccessControlTest.UserDBMock;
import CSCI5308.GroupFormationTool.AccessControlTest.UserPasswordHistoryMock;
import CSCI5308.GroupFormationTool.PasswordPolicy.IPasswordPolicy;
import CSCI5308.GroupFormationTool.PasswordPolicy.IPasswordPolicyPersistence;
import CSCI5308.GroupFormationTool.PasswordPolicy.PasswordPolicy;
import CSCI5308.GroupFormationTool.PasswordPolicy.PasswordPolicyImplementer;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;


@SpringBootTest()
@SuppressWarnings("deprecation")
public class PasswordPolicyEngineTest {

	IPasswordPolicyPersistence iPasswordPolicyPersistance = new PasswordPolicyDBMock();
	PasswordPolicy passwordPolicy = new PasswordPolicy(iPasswordPolicyPersistance);
	
	IPasswordPolicy iPasswordPolicy = new PasswordPolicyImplementer();
	IPasswordEncryption passwordEncryption = SystemConfig.instance().getPasswordEncryption();

	@BeforeAll
	public static void init(){
        System.out.println("Before All init() method called");
    }
		
	@Test
	public void testCorrectPassword() throws Exception {
		String password = "RAhuls@";
		String error = iPasswordPolicy.validatePassword(password, passwordPolicy);
		Assert.isTrue(error == "");
	}

	@Test
	public void testInvalidMaxLength() throws Exception {
		String password = "aaaaaaaaaaaaaaa";
		String error = iPasswordPolicy.validatePassword(password, passwordPolicy);
		Assert.isTrue(error == "Password Length is more than maximum length");
	}

	@Test
	public void testInvalidMinLength() throws Exception {
		String password = "aa";
		String error = iPasswordPolicy.validatePassword(password, passwordPolicy);
		Assert.isTrue(error == "Password Length is less than Minimum length");
	}

	@Test
	public void testDisallowedChars() throws Exception {
		String password = "qwertyu,";
		String error = iPasswordPolicy.validatePassword(password, passwordPolicy);
		Assert.isTrue(error == "Password contains disallowed characters");
	}

	@Test
	public void testMinimumUpperCase() throws Exception {
		String password = "Aaaaqwer";
		String error = iPasswordPolicy.validatePassword(password, passwordPolicy);
		Assert.isTrue(error == "Password should contain atleast minimum number of uppercase letters");
	}

	@Test
	public void testMinimumLowerCase() throws Exception {
		String password = "AAAAaa";
		String error = iPasswordPolicy.validatePassword(password, passwordPolicy);
		Assert.isTrue(error == "Password should contain atleast minimum number of lowercase letters");
	}

	@Test
	public void testMinimumSpecialChars() throws Exception {
		String password = "aaaaAA";
		String error = iPasswordPolicy.validatePassword(password, passwordPolicy);
		Assert.isTrue(error == "Password should contain atleast minimum number of special characters");
	}

	@Test
	public void testHistoryConstraint() throws Exception {
		UserPasswordHistoryMock uHist = new UserPasswordHistoryMock(); 
		
		User user = new User();
		IUserPersistence userMockObject = new UserDBMock();
		userMockObject.createUser(user);
		
		boolean matchFound = iPasswordPolicy.validatePasswordHistory(user, "RAHUL", passwordPolicy, uHist);
		Assert.isTrue(matchFound);
	}
}