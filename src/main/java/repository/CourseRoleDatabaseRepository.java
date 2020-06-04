package repository;

import exception.CopyCatMeDBConfigException;
import exception.CourseGroupFormationException;

public interface CourseRoleDatabaseRepository {

	boolean enrollUserInCourse(int id, Long courseId, int roleId)
			throws CopyCatMeDBConfigException, CourseGroupFormationException;

}
