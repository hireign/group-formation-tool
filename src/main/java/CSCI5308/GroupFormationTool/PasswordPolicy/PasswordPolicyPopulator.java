package CSCI5308.GroupFormationTool.PasswordPolicy;

import java.sql.ResultSet;
import java.sql.SQLException;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class PasswordPolicyPopulator implements IPasswordPolicyPersistence{

	public void populatePasswordPolicy(PasswordPolicy passwordPolicy){
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spLoadPasswordPolicy()");
			ResultSet results = proc.executeWithResults();
			while(results.next())
			{
				passwordPolicy.setMaximum_length(results.getInt("Maximum_length"));
				passwordPolicy.setMinimum_length(results.getInt("Minimum_length"));
				passwordPolicy.setMinimum_uppercase_Chars(results.getInt("Minimum_uppercase_Chars"));
				passwordPolicy.setMinimum_lowercase_Chars(results.getInt("Minimum_lowercase_Chars"));
				passwordPolicy.setMinimum_special_characters(results.getInt("Minimum_special_characters"));
				passwordPolicy.setDisallowed_Chars(results.getString("Disallowed_Chars"));
				passwordPolicy.setPassword_History_Constraint_No(results.getInt("Password_History_Constraint_No"));
			}
		}
		catch (SQLException e)
		{
			System.out.println("Exception Occurred");
			e.printStackTrace();
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
	}
}
