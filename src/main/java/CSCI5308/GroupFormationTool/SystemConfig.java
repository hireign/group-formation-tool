package CSCI5308.GroupFormationTool;

import CSCI5308.GroupFormationTool.Security.*;

import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.Database.*;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestionPersistence;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionAbstractFactory;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionDB;
import CSCI5308.GroupFormationTool.PasswordValidation.IPasswordValidatorEnumerator;
import CSCI5308.GroupFormationTool.PasswordValidation.IPasswordValidatorPersistence;
import CSCI5308.GroupFormationTool.PasswordValidation.PasswordAbstractFactory;
import CSCI5308.GroupFormationTool.PasswordValidation.PasswordValidatorDB;
import CSCI5308.GroupFormationTool.Courses.*;

public class SystemConfig
{
	private static SystemConfig uniqueInstance = null;
	
	private LoggerInterface logger;
	private IPasswordEncryption passwordEncryption;
	private IUserPersistence userDB;
	private IDatabaseConfiguration databaseConfiguration;
	private ICoursePersistence courseDB;
	private ICourseUserRelationshipPersistence courseUserRelationshipDB;
	private IQuestionPersistence questionDB;
	private IPasswordValidatorPersistence validatorDB;
	private IPasswordValidatorEnumerator passwordValidatorEnumerator;
	private CourseAbstractFactory courseFactory = CourseAbstractFactory.getFactory();
	
	private SystemConfig()
	{
		passwordEncryption = EncryptionAbstractFactory.getFactory().createEncrypter();
		userDB = UserAbstractFactory.getFactory().createUserDB();
		databaseConfiguration = DefaultDatabaseConfigurationFactory.getFactory().createDBConfig();
		courseDB = courseFactory.createCourseDB();
		courseUserRelationshipDB = courseFactory.createCourseUserPersistenceDB();
		questionDB = QuestionAbstractFactory.getFactory().createQuestionDB();
		validatorDB = PasswordAbstractFactory.getFactory().createPwdDB();
		logger = LoggerAbstractFactory.getFactory().createLoggerInstance();
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
	
	public IUserPersistence getUserDB()
	{
		return userDB;
	}
	
	public void setUserDB(IUserPersistence userDB)
	{
		this.userDB = userDB;
	}
	
	public IDatabaseConfiguration getDatabaseConfiguration()
	{
		return databaseConfiguration;
	}
	
	public void setDatabaseConfiguration(IDatabaseConfiguration databaseConfiguration)
	{
		this.databaseConfiguration = databaseConfiguration;
	}
	
	public void setCourseDB(ICoursePersistence courseDB)
	{
		this.courseDB = courseDB;
	}
	
	public ICoursePersistence getCourseDB()
	{
		return courseDB;
	}
	
	public void setCourseUserRelationshipDB(ICourseUserRelationshipPersistence courseUserRelationshipDB)
	{
		this.courseUserRelationshipDB = courseUserRelationshipDB;
	}
	
	public ICourseUserRelationshipPersistence getCourseUserRelationshipDB()
	{
		return courseUserRelationshipDB;
	}
	
	public void setQuestionDB(IQuestionPersistence questionDB)
	{
		this.questionDB = questionDB;
	}
	
	public IQuestionPersistence getQuestionDB()
	{
		return questionDB;
	}
	public void setPasswordValidatorDB(IPasswordValidatorPersistence validatorDB)
	{
		this.validatorDB = validatorDB;
	}
	
	public IPasswordValidatorPersistence getPasswordValidatorDB()
	{
		return validatorDB;
	}
	
	public void setPasswordValidatorEnumerator(IPasswordValidatorEnumerator passwordValidatorEnumerator)
	{
		this.passwordValidatorEnumerator = passwordValidatorEnumerator;
	}
	
	public IPasswordValidatorEnumerator getPasswordValidatorEnumerator()
	{
		return passwordValidatorEnumerator;
	}

	public LoggerInterface getLogger() {
		return logger;
	}

	public void setLogger(LoggerInterface logger) {
		this.logger = logger;
	}
	
	
	
}
