package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

public interface ICoursePersistence
{
	public List<Course> loadAllCourses() throws Exception;
	public void loadCourseByID(long id, Course course) throws Exception;
	public void createCourse(Course course) throws Exception;
	public boolean deleteCourse(long id) throws Exception;
}
