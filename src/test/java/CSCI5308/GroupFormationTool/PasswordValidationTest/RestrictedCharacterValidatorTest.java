package CSCI5308.GroupFormationTool.PasswordValidationTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestrictedCharacterValidatorTest 
{
	@Test
	public void isValid() 
	{
		String restrictedChar="#";
		String pass = "Pas@sed";
		assertThat(pass.contains(restrictedChar) == false).isTrue();
		pass = "Fai#led";
		assertThat(pass.contains(restrictedChar) == false).isFalse();
		
	}

}
