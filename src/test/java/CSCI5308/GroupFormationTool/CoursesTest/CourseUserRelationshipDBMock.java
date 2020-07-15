package CSCI5308.GroupFormationTool.CoursesTest;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Courses.ICourse;
import CSCI5308.GroupFormationTool.Courses.ICourseUserRelationshipPersistence;
import CSCI5308.GroupFormationTool.Courses.Role;

public class CourseUserRelationshipDBMock implements ICourseUserRelationshipPersistence
{
	public List<IUser> findAllUsersWithoutCourseRole(Role role, long courseID) 
	{
		List<IUser> userList = new ArrayList<>();
		User u = new User();
		u.setId(0);
		userList.add(u);
		u = new User();
		u.setId(1);
		userList.add(u);
		return userList;
	}

	public List<IUser> findAllUsersWithCourseRole(Role role, long courseID) 
	{
		List<IUser> userList = new ArrayList<>();
		User u = new User();
		u.setId(0);
		userList.add(u);
		u = new User();
		u.setId(1);
		userList.add(u);
		return userList;
	}

	public void enrollUser(ICourse course, IUser user, Role role) 
	{
		user.setId(0);
		course.setId(0);
		course.setTitle("Software Engineering");
	}

	public List<Role> loadUserRolesForCourse(ICourse course, IUser user) 
	{
		List<Role> userRoles = new ArrayList<>();
		userRoles.add(Role.STUDENT);
		userRoles.add(Role.TA);
		return userRoles;
	}

}