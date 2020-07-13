package CSCI5308.GroupFormationTool.PasswordValidationTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.PasswordValidation.IPasswordValidatorPersistence;

@SpringBootTest
class PasswordValidatorEnumeratorTest
{
	@Test
	public void getActiveValidators() throws Exception 
	{
		IPasswordValidatorPersistence validatorDB = new PasswordValidatorDBMock();
		HashMap<Long, String> activeValidators = validatorDB.loadActivePasswordValidators();
		assertThat(activeValidators.size()>0).isTrue();
		assertThat(activeValidators.size()==2).isTrue();
		for (@SuppressWarnings("rawtypes") Map.Entry item : activeValidators.entrySet()) 
		{
			String constraint = validatorDB.loadConstraintByValidatorId((long) item.getKey());
			assertThat(constraint.length()>0).isTrue();
			assertThat(constraint).isNotBlank();
			assertThat(constraint).isNotEmpty();
		}

	}
	
}
