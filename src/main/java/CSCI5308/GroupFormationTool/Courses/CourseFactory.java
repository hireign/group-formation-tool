package CSCI5308.GroupFormationTool.Courses;

public class CourseFactory extends CourseAbstractFactory {

	public ICoursePersistence createCourseDB() {
		return new CourseDB();
	}

	public ICourseUserRelationship createCourseUserDB() {
		return new CourseUserRelationship();
	}

	public ICourseUserRelationshipPersistence createCourseUserPersistenceDB() {
		return new CourseUserRelationshipDB();
	}

	public ICourse createCourse() {
		return new Course();
	}

}
