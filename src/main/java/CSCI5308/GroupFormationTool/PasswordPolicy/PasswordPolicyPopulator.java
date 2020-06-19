package CSCI5308.GroupFormationTool.PasswordPolicy;

import java.sql.ResultSet;
import java.sql.SQLException;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class PasswordPolicyPopulator implements IPasswordPolicyPersistence{

	private String maxLength = "Maximum_length";
	private String minLength = "Minimum_length";
	private String minUpper = "Minimum_uppercase_Chars";
	private String minLower = "Minimum_lowercase_Chars";
	private String minSpecial = "Minimum_special_characters";
	private String disallowed = "Disallowed_Chars";
	private String passwordHistoryConstraint = "Password_History_Constraint_No";
	
	public void populatePasswordPolicy(PasswordPolicy passwordPolicy){
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spLoadPasswordPolicy()");
			ResultSet results = proc.executeWithResults();
			while(results.next())
			{
				passwordPolicy.setMaximum_length(results.getInt(maxLength));
				passwordPolicy.setMinimum_length(results.getInt(minLength));
				passwordPolicy.setMinimum_uppercase_Chars(results.getInt(minUpper));
				passwordPolicy.setMinimum_lowercase_Chars(results.getInt(minLower));
				passwordPolicy.setMinimum_special_characters(results.getInt(minSpecial));
				passwordPolicy.setDisallowed_Chars(results.getString(disallowed));
				passwordPolicy.setPassword_History_Constraint_No(results.getInt(passwordHistoryConstraint));
			}
		}
		catch (SQLException e)
		{
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
