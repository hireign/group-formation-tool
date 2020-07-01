package CSCI5308.GroupFormationTool.PasswordValidation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class PasswordValidatorDB implements IPasswordValidatorPersistence
{
	@Override
	public HashMap<Long,String> loadActivePasswordValidators()
	{
		HashMap<Long,String> activeValidators = new HashMap<Long,String>();
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spLoadActivePasswordValidators()");
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					long id = results.getLong(1);
					String name = results.getString(2);
					activeValidators.put(id,name);
				}
			}
		}
		catch (SQLException e)
		{
			// Logging needed.
			System.out.println(e);
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return activeValidators;
	}
	
	@Override
	public String loadConstraintByValidatorId(long id)
	{
		CallStoredProcedure proc = null;
		String constraint=null;
		try
		{
			proc = new CallStoredProcedure("spLoadConstraintByValidator(?)");
			proc.setParameter(1, id);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					constraint = results.getString(1);
				}
			}
		}
		catch (SQLException e)
		{
			// Logging needed.
			System.out.println(e);
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return constraint;
	}
	
	@Override
	public List<String> fetchPreviousPasswordsByBannerID(String bannerID, int constraint) 
	{
		CallStoredProcedure proc = null;
		List<String> passwordList = new ArrayList<String>();
		try
		{
			proc = new CallStoredProcedure("spFetchPreviousPasswords(?,?)");
			proc.setParameter(1, bannerID);
			proc.setParameter(2, constraint);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					passwordList.add(results.getString(1));
				}
			}
		}
		catch (SQLException e)
		{
			// Logging needed.
			System.out.println(e);
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return passwordList;
	}
	
}
