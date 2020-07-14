package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import CSCI5308.GroupFormationTool.LoggerInterface;
import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDB implements ICoursePersistence
{
	
	private static LoggerInterface logger = SystemConfig.instance().getLogger();
	
	public List<ICourse> loadAllCourses() throws SQLException
	{
		List<ICourse> courses = new ArrayList<ICourse>();
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spLoadAllCourses()");
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					long id = results.getLong(1);
					String title = results.getString(2);
					ICourse c = CourseAbstractFactory.getFactory().createCourse();
					c.setId(id);
					c.setTitle(title);
					courses.add(c);
				}
			}
		}
		catch (SQLException e)
		{
			logger.error(CourseDB.class.toString(),String.format("action=getCourses status=failure Exception=" + e.getMessage()));
			throw e;
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return courses;
	}

	public void loadCourseByID(long id, ICourse course) throws SQLException
	{
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spFindCourseByID(?)");
			proc.setParameter(1, id);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					String title = results.getString(2);
					course.setId(id);
					course.setTitle(title);
				}
				logger.info(CourseDB.class.toString(),String.format("courseID=%d action=getCourseByID status=success",id));
			}
		}
		catch (SQLException e)
		{
			logger.error(CourseDB.class.toString(),String.format("courseID=%d action=getCourseByID status=failure exception e=%s",id,e.getMessage()));
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
	
	public void createCourse(ICourse course) throws SQLException
	{
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spCreateCourse(?, ?)");
			proc.setParameter(1, course.getTitle());
			proc.registerOutputParameterLong(2);
			proc.execute();
			logger.info(CourseDB.class.toString(),String.format("course=%s action=createCourse status=success",course.getTitle()));
		}
		catch (SQLException e)
		{
			logger.error(CourseDB.class.toString(),String.format("course=%s action=createCourse status=failure exception e=%s",course.getTitle(),e.getMessage()));
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
	
	public boolean deleteCourse(long id) throws SQLException
	{
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spDeleteCourse(?)");
			proc.setParameter(1, id);
			proc.execute();
			logger.info(CourseDB.class.toString(),String.format("courseID=%d action=deleteCourse status=success",id));
			return true;
		}
		catch (SQLException e)
		{
			logger.error(CourseDB.class.toString(),String.format("courseID=%d action=deleteCourse status=failure exception e=%s",id,e.getMessage()));
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
