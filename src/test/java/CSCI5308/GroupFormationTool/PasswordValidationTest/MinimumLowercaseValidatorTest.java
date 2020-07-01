package CSCI5308.GroupFormationTool.PasswordValidationTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MinimumLowercaseValidatorTest 
{
	@Test
	public void isValid() 
	{
		int minLowercase = 5;
		
		String pass = "Passed";
		int cntLower = countLowercase(pass);
		assertThat(cntLower>=minLowercase).isTrue();
		pass = "Fail";
		cntLower = countLowercase(pass);
		assertThat(cntLower>=minLowercase).isFalse();
		
	}
	
	public int countLowercase(String password) 
	{
		int cntLower=0;
		for (int i=0; i<password.length(); i++)
		{
			if (Character.isLowerCase(password.charAt(i)))
			{
				cntLower++;
			}
		}
		return cntLower;
	}

}
