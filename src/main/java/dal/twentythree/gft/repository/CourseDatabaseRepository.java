package dal.twentythree.gft.repository;

import java.util.List;

import dal.twentythree.gft.dao.Course;
import dal.twentythree.gft.exception.CopyCatMeDBConfigException;
import dal.twentythree.gft.exception.CourseGroupFormationException;

public interface CourseDatabaseRepository {
	
	public List<Course> getAllCourses() throws CopyCatMeDBConfigException, CourseGroupFormationException;
	
	public Course fetchByCourseId(String courseId) throws CopyCatMeDBConfigException, CourseGroupFormationException;
	
}
