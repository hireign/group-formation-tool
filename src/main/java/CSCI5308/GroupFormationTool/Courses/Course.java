package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.AccessControl.User;

public class Course
{
	private long id;
	private String title;
	private ICourseUserRelationship userRoleDecider;
	
	public Course()
	{
		setDefaults();
	}
	
	public Course(long id, ICoursePersistence courseDB) throws Exception
	{
		setDefaults();
		courseDB.loadCourseByID(id, this);
	}
	
	public void setDefaults()
	{
		id = -1;
		title = "";
		userRoleDecider = new CourseUserRelationship();
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public long getId()
	{
		return id;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public boolean delete(ICoursePersistence courseDB) throws Exception
	{
		return courseDB.deleteCourse(id);
	}
	
	public void createCourse(ICoursePersistence courseDB) throws Exception
	{
		courseDB.createCourse(this);
	}
	
	public void enrollUserInCourse(Role role, User user) throws Exception
	{
		userRoleDecider.enrollUserInCourse(user, this, role);
	}
	
	public boolean isCurrentUserEnrolledAsRoleInCourse(Role role) throws Exception
	{
		return userRoleDecider.userHasRoleInCourse(CurrentUser.instance().getCurrentAuthenticatedUser(), role, this);
	}

	public boolean isCurrentUserEnrolledAsRoleInCourse(String role) throws Exception
	{
		Role r = Role.valueOf(role.toUpperCase());
		return isCurrentUserEnrolledAsRoleInCourse(r);
	}
	
	public List<Role> getAllRolesForCurrentUserInCourse() throws Exception
	{
		return userRoleDecider.loadAllRoluesForUserInCourse(CurrentUser.instance().getCurrentAuthenticatedUser(), this);
	}
}
