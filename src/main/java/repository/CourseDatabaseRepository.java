package repository;

import java.util.List;

import dao.Course;
import exception.CopyCatMeDBConfigException;
import exception.CourseGroupFormationException;

public interface CourseDatabaseRepository {
	
	public List<Course> getAllCourses() throws CopyCatMeDBConfigException, CourseGroupFormationException;
	
	public Course fetchByCourseId(String courseId) throws CopyCatMeDBConfigException, CourseGroupFormationException;
	
}
