package util;

import repository.CourseDatabaseRepository;
import repository.CourseRoleDatabaseRepository;
import repository.impl.CourseDatabaseRepositoryImpl;
import repository.impl.CourseRoleDatabaseRepositoryImpl;

public class SystemConfig
{
	private static SystemConfig uniqueInstance = null;
	private IPasswordEncryption passwordEncryption;
	private CourseDatabaseRepository courseDB;
	private CourseRoleDatabaseRepository courseRole;

	private SystemConfig()
	{
		passwordEncryption = new BCryptPasswordEncryption();
		courseDB = new CourseDatabaseRepositoryImpl();
		courseRole = new CourseRoleDatabaseRepositoryImpl();
	}
	
	public static SystemConfig instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new SystemConfig();
		}
		return uniqueInstance;
	}
	
	public IPasswordEncryption getPasswordEncryption()
	{
		return passwordEncryption;
	}
	
	public void setPasswordEncryption(IPasswordEncryption passwordEncryption)
	{
		this.passwordEncryption = passwordEncryption;
	}
	
	public CourseDatabaseRepository getCourseDB() {
		return courseDB;
	}

	public void setCourseDB(CourseDatabaseRepository courseDB) {
		this.courseDB = courseDB;
	}

	public CourseRoleDatabaseRepository getCourseRole() {
		return courseRole;
	}

	public void setCourseRole(CourseRoleDatabaseRepository courseRole) {
		this.courseRole = courseRole;
	}
}
