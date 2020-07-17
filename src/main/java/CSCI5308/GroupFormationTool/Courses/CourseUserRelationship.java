package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.SystemConfig;

public class CourseUserRelationship implements ICourseUserRelationship {
	public boolean userHasRoleInCourse(IUser user, Role role, ICourse course) throws Exception {
		if (null == user || user.isInvalidUser()) {
			return false;
		}
		if (null == role) {
			return false;
		}
		if (null == course) {
			return false;
		}
		ICourseUserRelationshipPersistence userCourseRelationshipDB = SystemConfig.instance()
				.getCourseUserRelationshipDB();
		List<Role> roles = userCourseRelationshipDB.loadUserRolesForCourse(course, user);
		if (null != roles && roles.contains(role)) {
			return true;
		}
		return false;
	}

	public List<Role> loadAllRoluesForUserInCourse(IUser user, ICourse course) throws Exception {
		ICourseUserRelationshipPersistence userCourseRelationshipDB = SystemConfig.instance()
				.getCourseUserRelationshipDB();
		List<Role> roles = userCourseRelationshipDB.loadUserRolesForCourse(course, user);
		return roles;
	}

	public void enrollUserInCourse(IUser user, ICourse course, Role role) throws Exception {
		ICourseUserRelationshipPersistence userCourseRelationshipDB = SystemConfig.instance()
				.getCourseUserRelationshipDB();
		userCourseRelationshipDB.enrollUser(course, user, role);
	}
}
