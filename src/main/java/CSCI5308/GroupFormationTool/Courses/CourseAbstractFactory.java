package CSCI5308.GroupFormationTool.Courses;

import org.springframework.web.multipart.MultipartFile;

public abstract class CourseAbstractFactory {

	private static final CourseFactory courseFactory = new CourseFactory();

	public static CourseAbstractFactory getFactory() {
		return courseFactory;
	}

	public abstract ICoursePersistence makeCourseDB();

	public abstract ICourseUserRelationship makeCourseUserDB();

	public abstract ICourseUserRelationshipPersistence makeCourseUserPersistenceDB();

	public abstract ICourse makeCourse();

	public abstract ICourse makeCourse(long id, ICoursePersistence courseDB) throws Exception;

	public abstract IStudentCSVParser makeCSVParser(MultipartFile file);

	public abstract IStudentCSVImport makeCSVImporter(IStudentCSVParser parser, ICourse course);
}
