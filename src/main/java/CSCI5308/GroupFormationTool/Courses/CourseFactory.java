package CSCI5308.GroupFormationTool.Courses;

import org.springframework.web.multipart.MultipartFile;

public class CourseFactory extends CourseAbstractFactory {

	public ICoursePersistence makeCourseDB() {
		return new CourseDB();
	}

	public ICourseUserRelationship makeCourseUserDB() {
		return new CourseUserRelationship();
	}

	public ICourseUserRelationshipPersistence makeCourseUserPersistenceDB() {
		return new CourseUserRelationshipDB();
	}

	public ICourse makeCourse() {
		return new Course();
	}

	public ICourse makeCourse(long id, ICoursePersistence courseDB) throws Exception {
		return new Course(id, courseDB);
	}

	@Override
	public IStudentCSVParser makeCSVParser(MultipartFile file) {
		return new StudentCSVParser(file);
	}

	@Override
	public IStudentCSVImport makeCSVImporter(IStudentCSVParser parser, ICourse course) {
		return new StudentCSVImport(parser, course);
	}

}
