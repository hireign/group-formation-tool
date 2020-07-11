package CSCI5308.GroupFormationTool.Courses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.LoggerUtil;
import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class CourseUserRelationshipDB implements ICourseUserRelationshipPersistence
{
	
	private static LoggerUtil logger = SystemConfig.instance().getLogger();
	
	public List<User> findAllUsersWithoutCourseRole(Role role, long courseID) throws SQLException
	{
		List<User> users = new ArrayList<User>();
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spFindUsersWithoutCourseRole(?, ?)");
			proc.setParameter(1, role.toString());
			proc.setParameter(2,  courseID);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					long userID = results.getLong(1);
					String bannerID = results.getString(2);
					String firstName = results.getString(3);
					String lastName = results.getString(4);
					User u = new User();
					u.setID(userID);
					u.setBannerID(bannerID);
					u.setFirstName(firstName);
					u.setLastName(lastName);
					users.add(u);
				}
				logger.info(CourseUserRelationshipDB.class.toString(),String.format("course=%d role=%s action=findAllUsersWithoutCourseRole status=success",courseID,role.toString()));
			}
		}
		catch (SQLException e)
		{
			logger.error(CourseUserRelationshipDB.class.toString(),String.format("course=%d role=%s action=findAllUsersWithoutCourseRole status=failure exception e=%s",courseID,role.toString(),e.getMessage()));
			throw e;
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return users;
	}

	public List<User> findAllUsersWithCourseRole(Role role, long courseID) throws SQLException
	{
		List<User> users = new ArrayList<User>();
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spFindUsersWithCourseRole(?, ?)");
			proc.setParameter(1, role.toString());
			proc.setParameter(2,  courseID);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					long userID = results.getLong(1);
					User u = new User();
					u.setID(userID);
					users.add(u);
				}
				logger.info(CourseUserRelationshipDB.class.toString(),String.format("course=%d role=%s action=findAllUsersWithCourseRole status=success",courseID,role.toString()));
			}
		}
		catch (SQLException e)
		{
			logger.error(CourseUserRelationshipDB.class.toString(),String.format("course=%d role=%s action=findAllUsersWithCourseRole status=failure exception e=%s",courseID,role.toString(),e.getMessage()));
			throw e;
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return users;
	}
	
	public void enrollUser(Course course, User user, Role role) throws SQLException
	{
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spEnrollUser(?, ?, ?)");
			proc.setParameter(1, course.getId());
			proc.setParameter(2, user.getID());
			proc.setParameter(3, role.toString());
			proc.execute();
			logger.info(CourseUserRelationshipDB.class.toString(),String.format("user=%s course=%d action=enrollUser status=success",user.getBannerID(),course.getId()));
		}
		catch (SQLException e)
		{
			logger.error(CourseUserRelationshipDB.class.toString(),String.format("user=%s course=%d action=enrollUser status=failure exception e=%s",user.getBannerID(),course.getId(),e.getMessage()));
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

	public List<Role> loadUserRolesForCourse(Course course, User user) throws SQLException
	{
		List<Role> roles = new ArrayList<Role>();
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spLoadUserRolesForCourse(?, ?)");
			proc.setParameter(1, course.getId());
			proc.setParameter(2, user.getID());
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					Role role = Role.valueOf(results.getString(1).toUpperCase());
					roles.add(role);
				}
				logger.info(CourseUserRelationshipDB.class.toString(),String.format("user=%s course=%d action=loadUserRolesForCourse status=success",user.getBannerID(),course.getId()));
			}
		}
		catch (SQLException e)
		{
			logger.error(CourseUserRelationshipDB.class.toString(),String.format("user=%s course=%d action=loadUserRolesForCourse status=failure exception e=%s",user.getBannerID(),course.getId(),e.getMessage()));
			throw e;
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return roles;
	}
}
