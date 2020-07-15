package CSCI5308.GroupFormationTool.CoursesTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.TestSystemConfig;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.CourseAbstractFactory;
import CSCI5308.GroupFormationTool.Courses.ICourse;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;

@SpringBootTest
@SuppressWarnings("deprecation")
class CourseTest 
{
	private CourseAbstractFactory courseFactory = CourseAbstractFactory.getFactory();
	
	@Test
	public void ConstructorTests() throws Exception 
	{
		ICourse course = courseFactory.createCourse();
		Assert.isTrue(course.getId() == -1);
		Assert.isTrue(course.getTitle().isEmpty());

		ICoursePersistence courseDB = TestSystemConfig.instance().getCourseDB();
		course = courseFactory.createCourse(0, courseDB);
		Assert.isTrue(course.getId() == 0);
		Assert.isTrue(course.getTitle().equals("Software Engineering"));
	}

	@Test
	public void setIdTest() 
	{
		ICourse course = courseFactory.createCourse();
		course.setId(7);
		Assert.isTrue(course.getId() == 7);
	}

	@Test
	public void getIdTest() 
	{
		ICourse course = courseFactory.createCourse();
		course.setId(7);
		Assert.isTrue(course.getId() == 7);
	}

	@Test
	public void setTitleTest() 
	{
		ICourse course = courseFactory.createCourse();
		course.setTitle("Advanced Topics in Software Development");
		Assert.isTrue(course.getTitle().equals("Advanced Topics in Software Development"));
	}

	@Test
	public void getTitleTest() 
	{
		ICourse course = courseFactory.createCourse();
		course.setTitle("Advanced Topics in Software Development");
		Assert.isTrue(course.getTitle().equals("Advanced Topics in Software Development"));
	}

	@Test
	public void deleteCourseTest() throws Exception 
	{
		ICoursePersistence courseDB = TestSystemConfig.instance().getCourseDB();
		boolean status = courseDB.deleteCourse(0);
		Assert.isTrue(status);
	}

	@Test
	public void createCourseTest() throws Exception 
	{
		ICoursePersistence courseDB =TestSystemConfig.instance().getCourseDB();
		ICourse course = courseFactory.createCourse();
		course.setId(0);
		course.setTitle("Software Engineering");
		courseDB.createCourse(course);
		Assert.isTrue(course.getId() == 0);
		Assert.isTrue(course.getTitle().equals("Software Engineering"));
	}

}
