package CSCI5308.GroupFormationTool.PasswordValidationTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MinimumSymbolValidatorTest 
{
	@Test
	public void isValid() 
	{
		int minSymbols = 1;
		String pass = "Pas@sed";
		int cntSymbols = countSymbols(pass);
		assertThat(cntSymbols>=minSymbols).isTrue();
		pass = "fail";
		cntSymbols = countSymbols(pass);
		assertThat(cntSymbols>=minSymbols).isFalse();
		
	}
	
	public int countSymbols(String password) 
	{
		int cntSymbols=0;
		char[] ch = password.toCharArray();
		for (int i = 0; i < ch.length; i++) 
		{ 
			if(Character.isLetter(ch[i]) == false 
					&& Character.isDigit(ch[i]) == false) 
			{
				cntSymbols++;
			}
		}
		return cntSymbols;
	}

}
