package CSCI5308.GroupFormationTool.Courses;

import org.springframework.web.multipart.MultipartFile;

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

	public ICourse createCourse(long id, ICoursePersistence courseDB) throws Exception {
		return new Course(id, courseDB);
	}

	@Override
	public IStudentCSVParser createCSVParser(MultipartFile file) {
		return new StudentCSVParser(file);
	}

	@Override
	public IStudentCSVImport createCSVImporter(IStudentCSVParser parser, ICourse course) {
		return new StudentCSVImport(parser, course);
	}

}
