package CSCI5308.GroupFormationTool.PasswordPolicy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class UserPasswordHistory implements IUserPasswordHistoryPersistence{

	@Override
	public ArrayList<String> checkPasswordExistsWRTLimit(long id, int limit) {
		ArrayList<String> response= new ArrayList<>();
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spLoadHistoricalPasswords(?, ?)");
			proc.setParameter(1, id);
			proc.setParameter(2, limit);
			ResultSet results = proc.executeWithResults();
			
			while(results.next())
			{
				response.add( results.getString("password")) ;
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
		
		return response;
	}

}
