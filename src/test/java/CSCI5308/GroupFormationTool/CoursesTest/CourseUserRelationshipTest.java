package CSCI5308.GroupFormationTool.CoursesTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.TestSystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControlTest.CurrentUserMock;
import CSCI5308.GroupFormationTool.Courses.CourseAbstractFactory;
import CSCI5308.GroupFormationTool.Courses.ICourse;
import CSCI5308.GroupFormationTool.Courses.ICourseUserRelationshipPersistence;
import CSCI5308.GroupFormationTool.Courses.Role;

@SpringBootTest
@SuppressWarnings("deprecation")
class CourseUserRelationshipTest 
{
	private ICourseUserRelationshipPersistence courseUserRelationshipDB;
	private CourseAbstractFactory courseFactory = CourseAbstractFactory.getFactory();

	public CourseUserRelationshipTest() 
	{
		courseUserRelationshipDB = TestSystemConfig.instance().getCourseUserRelationshipDB();
	}

	@Test
	public void userHasRoleInCourse() throws Exception 
	{
		ICourse course = courseFactory.makeCourse();
		course.setId(0);
		CurrentUserMock currentUser = TestSystemConfig.instance().getCurrentUser();
		IUser user = currentUser.getCurrentAuthenticatedUser();
		List<Role> roles = courseUserRelationshipDB.loadUserRolesForCourse(course, user);
		assertThat(roles).isNotNull();
		assertThat(roles).isNotEmpty();
		Assert.isTrue(roles.size() > 0);
	}

	@Test
	public void loadAllRoluesForUserInCourse() throws Exception 
	{
		ICourse course = courseFactory.makeCourse();
		course.setId(0);
		CurrentUserMock currentUser = TestSystemConfig.instance().getCurrentUser();
		IUser user = currentUser.getCurrentAuthenticatedUser();
		List<Role> roles = courseUserRelationshipDB.loadUserRolesForCourse(course, user);
		Assert.isTrue(roles.size() > 0);
	}

	@Test
	public void enrollUserInCourse() throws Exception 
	{
		ICourse course = courseFactory.makeCourse();
		CurrentUserMock currentUser = TestSystemConfig.instance().getCurrentUser();
		IUser user = currentUser.getCurrentAuthenticatedUser();
		courseUserRelationshipDB.enrollUser(course, user, Role.STUDENT);
		assertThat(course.getTitle().equalsIgnoreCase("Software Engineering"));
	}

}
