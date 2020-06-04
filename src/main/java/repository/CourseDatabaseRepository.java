package repository;

import java.util.List;

import dao.AdminCourse;
import dao.Course;
import exception.CopyCatMeDBConfigException;
import exception.CourseGroupFormationException;

public interface CourseDatabaseRepository {
	
	public List<Course> getAllCourses() throws CopyCatMeDBConfigException, CourseGroupFormationException;
//	public Course fetchByCourseId(String courseId) throws CopyCatMeDBConfigException, CourseGroupFormationException;
	public Course loadCourseByID(long courseID, Course course) throws CourseGroupFormationException, CopyCatMeDBConfigException;
	public boolean isCurrentUserEnrolledAsRoleInCourse(int instructorRole);
	public String deleteCourseById(String courseName) throws CopyCatMeDBConfigException, CourseGroupFormationException;
	public String addCourse(AdminCourse course) throws CopyCatMeDBConfigException, CourseGroupFormationException;

}
