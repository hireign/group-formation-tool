package CSCI5308.GroupFormationTool.PasswordValidationTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.PasswordValidation.IPasswordValidatorPersistence;

@SpringBootTest
class PasswordHistoryValidatorTest 
{
	@Test
	public void isValid() throws Exception 
	{
		IPasswordValidatorPersistence validatorDB = new PasswordValidatorDBMock();
		int historyCount=3;
		String pass="pass"; 
		List<String> passwordList = validatorDB.fetchPreviousPasswordsByBannerID("B000000", historyCount);
		assertThat(passwordList.contains(pass) == false).isTrue();
		pass="fail";
		passwordList = validatorDB.fetchPreviousPasswordsByBannerID("B1234567", historyCount);
		assertThat(passwordList.contains(pass) == false).isFalse();
	}

}
