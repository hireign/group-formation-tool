package CSCI5308.GroupFormationTool.Courses;

import org.springframework.web.multipart.MultipartFile;

public abstract class CourseAbstractFactory {
	
	private static final CourseFactory courseFactory = new CourseFactory();
	
	public static CourseAbstractFactory getFactory() {
		return courseFactory;
	}
	
	public abstract ICoursePersistence createCourseDB();
	public abstract ICourseUserRelationship createCourseUserDB();
	public abstract ICourseUserRelationshipPersistence createCourseUserPersistenceDB();
	public abstract ICourse createCourse();
	public abstract ICourse createCourse(long id, ICoursePersistence courseDB) throws Exception;
	public abstract IStudentCSVParser createCSVParser(MultipartFile file);
	public abstract IStudentCSVImport createCSVImporter(IStudentCSVParser parser, ICourse course);
}
