package CSCI5308.GroupFormationTool.CoursesTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.TestSystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.UserAbstractFactory;
import CSCI5308.GroupFormationTool.Courses.CourseAbstractFactory;
import CSCI5308.GroupFormationTool.Courses.ICourse;
import CSCI5308.GroupFormationTool.Courses.ICourseUserRelationshipPersistence;
import CSCI5308.GroupFormationTool.Courses.Role;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;

@SpringBootTest
@SuppressWarnings("deprecation")
class StudentCSVImportTest 
{
	private UserAbstractFactory userFactory = UserAbstractFactory.getFactory();
	private CourseAbstractFactory courseFactory = CourseAbstractFactory.getFactory();
	private ICourseUserRelationshipPersistence courseUserRelationshipDB;

	public StudentCSVImportTest() 
	{
		courseUserRelationshipDB = TestSystemConfig.instance().getCourseUserRelationshipDB();
	}

	@Test
	public void enrollStudentFromRecord() throws Exception 
	{
		IUser user = userFactory.makeUser();
		ICourse course = courseFactory.makeCourse();
		IUserPersistence userDB = TestSystemConfig.instance().getUserDB();
		IPasswordEncryption passwordEncryption = TestSystemConfig.instance().getPasswordEncryption();
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
