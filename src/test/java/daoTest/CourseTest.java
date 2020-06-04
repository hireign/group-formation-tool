package daoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dao.Course;

@SpringBootTest(classes=CourseTest.class)
public class CourseTest {

	@Test
	public void setCourseIdTest() {
		Course course = new Course();
		course.setCourseId((long) 5409);
		assertEquals(5409,course.getCourseId());
	}
	
	@Test
	public void getCourseIdTest() {
		Course course = new Course();
		course.setCourseId((long) 5409);
		assertEquals(5409,course.getCourseId());
	}
	
	@Test
	public void setCourseNameTest() {
		Course course = new Course();
		course.setCourseName("Advanced Topics In Software Development");
		assertEquals("Advanced Topics In Software Development",course.getCourseName());
	}
	
	@Test
	public void getCourseNameTest() {
		Course course = new Course();
		course.setCourseName("Advanced Topics In Software Development");
		assertEquals("Advanced Topics In Software Development",course.getCourseName());
	}
	
}
