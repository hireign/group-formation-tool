package CSCI5308.GroupFormationTool.PasswordValidationTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MinimumUppercaseValidatorTest 
{
	@Test
	public void isValid() 
	{
		int minUppercase = 1;
		String pass = "Passed";
		int cntUpper = countUppercase(pass);
		assertThat(cntUpper>=minUppercase).isTrue();
		pass = "fail";
		cntUpper = countUppercase(pass);
		assertThat(cntUpper>=minUppercase).isFalse();
		
	}
	
	public int countUppercase(String password) 
	{	
		int cntUpper=0;
		for (int i=0; i<password.length(); i++)
		{
			if (Character.isUpperCase(password.charAt(i))) 
			{
				cntUpper++;
			}
		}
		return cntUpper;
	}

}
