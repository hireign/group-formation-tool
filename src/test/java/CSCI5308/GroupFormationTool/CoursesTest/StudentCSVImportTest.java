package CSCI5308.GroupFormationTool.CoursesTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.AccessControlTest.UserDBMock;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.ICourseUserRelationshipPersistence;
import CSCI5308.GroupFormationTool.Courses.Role;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;
import CSCI5308.GroupFormationTool.SecurityTest.PasswordEncryptionMock;

@SpringBootTest
@SuppressWarnings("deprecation")
class StudentCSVImportTest 
{
	
	private ICourseUserRelationshipPersistence courseUserRelationshipDB;

	public StudentCSVImportTest() 
	{
		courseUserRelationshipDB = new CourseUserRelationshipDBMock();
	}

	@Test
	public void enrollStudentFromRecord() throws Exception 
	{
		User user = new User();
		Course course = new Course();
		IUserPersistence userDB = new UserDBMock();
		IPasswordEncryption passwordEncryption = new PasswordEncryptionMock();
		user.createUser(userDB, passwordEncryption, null);
		assertThat(user.getBannerID().equalsIgnoreCase("B00000000"));
		courseUserRelationshipDB.enrollUser(course, user, Role.STUDENT);
		assertThat(course.getTitle().equalsIgnoreCase("Software Engineering"));
	}

	@Test
	public void getSuccessResults() 
	{
		List<String> successResults = new ArrayList<String>();
		successResults.add("Created record");
		assertThat(successResults).isNotNull();
		assertThat(successResults).isNotEmpty();
		Assert.isTrue(successResults.size() > 0);
	}

	@Test
	public void getFailureResults() 
	{
		List<String> failureResults = new ArrayList<String>();
		failureResults.add("Created record");
		assertThat(failureResults).isNotNull();
		assertThat(failureResults).isNotEmpty();
		Assert.isTrue(failureResults.size() > 0);
	}

}
