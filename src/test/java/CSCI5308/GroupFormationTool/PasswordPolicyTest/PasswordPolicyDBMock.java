package CSCI5308.GroupFormationTool.PasswordPolicyTest;

import CSCI5308.GroupFormationTool.PasswordPolicy.IPasswordPolicyPersistence;
import CSCI5308.GroupFormationTool.PasswordPolicy.PasswordPolicy;

public class PasswordPolicyDBMock implements IPasswordPolicyPersistence {

	@Override
	public void populatePasswordPolicy(PasswordPolicy passwordPolicy) {

		passwordPolicy.setMaximum_length(10);
		passwordPolicy.setMinimum_length(5);
		passwordPolicy.setMinimum_uppercase_Chars(2);
		passwordPolicy.setMinimum_lowercase_Chars(4);
		passwordPolicy.setMinimum_special_characters(1);
		passwordPolicy.setDisallowed_Chars(",/");
		passwordPolicy.setPassword_History_Constraint_No(2);
		
	}

}
