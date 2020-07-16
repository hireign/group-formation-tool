package CSCI5308.GroupFormationTool.CoursesTest;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.Courses.CourseAbstractFactory;
import CSCI5308.GroupFormationTool.Courses.ICourse;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;

public class CourseDBMock implements ICoursePersistence 
{
	private CourseAbstractFactory courseFactory = CourseAbstractFactory.getFactory();
	
	public List<ICourse> loadAllCourses() 
	{
		List<ICourse> courseList = new ArrayList<>();
		ICourse course = courseFactory.makeCourse();
		course.setId(0);
		course.setTitle("Software Engineering");
		courseList.add(course);
		course = courseFactory.makeCourse();
		course.setId(1);
		course.setTitle("Advanced Topics in Software Development");
		courseList.add(course);
		return courseList;
	}

	public void loadCourseByID(long id, ICourse course) 
	{
		course.setId(id);
		course.setTitle("Software Engineering");
	}

	public void createCourse(ICourse course) 
	{
		course.setId(0);
		course.setTitle("Software Engineering");
	}

	public boolean deleteCourse(long id) 
	{
		ICourse course = courseFactory.makeCourse();
		course.setId(id);
		course.setTitle("Software Engineering");
		course.setDefaults();
		return true;
	}

}
