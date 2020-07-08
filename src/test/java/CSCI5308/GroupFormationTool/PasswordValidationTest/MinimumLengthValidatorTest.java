package CSCI5308.GroupFormationTool.PasswordValidationTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MinimumLengthValidatorTest 
{
	@Test
	public void isValid() 
	{
		int minLength = 5;
		String pass = "passed";
		assertThat(pass.length()>=minLength).isTrue();
		pass = "fail";
		assertThat(pass.length()>=minLength).isFalse();
	}

}
