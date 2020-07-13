package CSCI5308.GroupFormationTool.AccessControl;

import java.sql.ResultSet;
import java.sql.SQLException;

import CSCI5308.GroupFormationTool.LoggerUtil;
import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class UserDB implements IUserPersistence
{	
	
	private static LoggerUtil logger = SystemConfig.instance().getLogger();
	
	public void loadUserByID(long id, User user) throws SQLException
	{
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spLoadUser(?)");
			proc.setParameter(1, id);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					long userID = results.getLong(1);
					String bannerID = results.getString(2);
					String password = results.getString(3);
					String firstName = results.getString(4);
					String lastName = results.getString(5);
					String email = results.getString(6);
					user.setID(userID);
					user.setBannerID(bannerID);
					user.setPassword(password);
					user.setFirstName(firstName);
					user.setLastName(lastName);
					user.setEmail(email);
				}
				logger.info(UserDB.class.toString(),String.format("userID=%d action=getUserByID status=success",id));
			}
		}
		catch (SQLException e)
		{
			logger.error(UserDB.class.toString(),String.format("userID=%d action=getUserByID status=failure exception e=%s",id,e.getMessage()));
			throw e;
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
	}

	public void loadUserByBannerID(String bannerID, User user) throws SQLException
	{
		CallStoredProcedure proc = null;
		long userID = -1;
		try
		{
			proc = new CallStoredProcedure("spFindUserByBannerID(?)");
			proc.setParameter(1, bannerID);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					userID = results.getLong(1);
				}
			}
		}
		catch (SQLException e)
		{
			logger.error(UserDB.class.toString(),String.format("userID=%s action=getUserByBannerID status=failure exception e=%s",bannerID,e.getMessage()));
			throw e;
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		
		if (userID > -1)
		{
			loadUserByID(userID, user);
		}
	}
	
	public void createUser(User user) throws SQLException
	{
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spCreateUser(?, ?, ?, ?, ?, ?)");
			proc.setParameter(1, user.getBannerID());
			proc.setParameter(2, user.getPassword());
			proc.setParameter(3, user.getFirstName());
			proc.setParameter(4, user.getLastName());
			proc.setParameter(5, user.getEmail());
			proc.registerOutputParameterLong(6);
			proc.execute();
			logger.info(UserDB.class.toString(),String.format("user=%s action=createUser status=success",user.getBannerID()));
		}
		catch (SQLException e)
		{
			logger.error(UserDB.class.toString(),String.format("user=%s action=createUser status=failure exception e=%s",user.getBannerID(),e.getMessage()));
			throw e;
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
