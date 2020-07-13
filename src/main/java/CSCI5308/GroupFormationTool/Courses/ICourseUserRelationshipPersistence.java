package CSCI5308.GroupFormationTool.Courses;

import java.sql.SQLException;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;

public interface ICourseUserRelationshipPersistence
{
	public List<User> findAllUsersWithoutCourseRole(Role role, long courseID) throws Exception;
	public List<User> findAllUsersWithCourseRole(Role role, long courseID) throws Exception;
	public void enrollUser(Course course, User user, Role role) throws Exception;
	public List<Role> loadUserRolesForCourse(Course course, User user) throws Exception;
}
