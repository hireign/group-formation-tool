package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.IUser;

public interface ICourse {

	void setDefaults();

	void setId(long id);

	long getId();

	void setTitle(String title);

	String getTitle();

	boolean delete(ICoursePersistence courseDB) throws Exception;

	void createCourse(ICoursePersistence courseDB) throws Exception;

	void enrollUserInCourse(Role role, IUser user) throws Exception;

	boolean isCurrentUserEnrolledAsRoleInCourse(Role role) throws Exception;

	boolean isCurrentUserEnrolledAsRoleInCourse(String role) throws Exception;

	List<Role> getAllRolesForCurrentUserInCourse() throws Exception;

}