package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;

public interface ICourseUserRelationship
{
	public boolean userHasRoleInCourse(User user, Role role, Course course) throws Exception;
	public List<Role> loadAllRoluesForUserInCourse(User user, Course course) throws Exception;
	public void enrollUserInCourse(User user, Course course, Role role) throws Exception;
}
