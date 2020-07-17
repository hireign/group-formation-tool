package CSCI5308.GroupFormationTool.PasswordValidationTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MaximumLengthValidatorTest 
{
	@Test
	public void isValid() 
	{
		int maxLength = 5;
		String pass = "pass";
		assertThat(pass.length()<=maxLength).isTrue();
		pass = "failed";
		assertThat(pass.length()<=maxLength).isFalse();
	}

}
