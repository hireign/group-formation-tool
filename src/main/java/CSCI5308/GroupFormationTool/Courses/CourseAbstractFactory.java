package CSCI5308.GroupFormationTool.Courses;

public abstract class CourseAbstractFactory {
	
	private static final CourseFactory courseFactory = new CourseFactory();
	
	public abstract ICoursePersistence createCourseDB();
	public abstract ICourseUserRelationship createCourseUserDB();
	public abstract ICourseUserRelationshipPersistence createCourseUserPersistenceDB();
	public abstract ICourse createCourse();
	
	public static CourseAbstractFactory getFactory() {
		return courseFactory;
	}

}
