package repository;

import dao.AdminCourse;
import dao.CourseRole;
import exception.CopyCatMeDBConfigException;
import exception.CourseGroupFormationException;

import java.util.List;

public interface CourseRoleDatabaseRepository {

	public boolean enrollUserInCourse(int id, Long courseId, int roleId)
			throws CopyCatMeDBConfigException, CourseGroupFormationException;
	public List<CourseRole> getAllCourseRoleInstructor() throws CopyCatMeDBConfigException, CourseGroupFormationException;
	public String deleteInstructorById(String userID) throws CopyCatMeDBConfigException, CourseGroupFormationException;
	public String addInstructor(CourseRole instructor) throws CopyCatMeDBConfigException, CourseGroupFormationException;

}
