package CSCI5308.GroupFormationTool;

import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.UserDB;
import CSCI5308.GroupFormationTool.Courses.CourseDB;
import CSCI5308.GroupFormationTool.Courses.CourseUserRelationshipDB;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.Courses.ICourseUserRelationshipPersistence;
import CSCI5308.GroupFormationTool.Database.DefaultDatabaseConfiguration;
import CSCI5308.GroupFormationTool.Database.IDatabaseConfiguration;
import CSCI5308.GroupFormationTool.PasswordValidation.IPasswordValidatorEnumerator;
import CSCI5308.GroupFormationTool.PasswordValidation.IPasswordValidatorPersistence;
import CSCI5308.GroupFormationTool.PasswordValidation.PasswordValidatorDB;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestionPersistence;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionDB;
import CSCI5308.GroupFormationTool.Security.BCryptPasswordEncryption;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;
import CSCI5308.GroupFormationTool.SurveyManager.ISurveyPersistence;
import CSCI5308.GroupFormationTool.SurveyManager.SurveyDB;


public class SystemConfig
{
	private static SystemConfig uniqueInstance = null;
	
	private LoggerUtil logger;
	private IPasswordEncryption passwordEncryption;
	private IUserPersistence userDB;
	private IDatabaseConfiguration databaseConfiguration;
	private ICoursePersistence courseDB;
	private ICourseUserRelationshipPersistence courseUserRelationshipDB;
	private IQuestionPersistence questionDB;
	private IPasswordValidatorPersistence validatorDB;
	private IPasswordValidatorEnumerator passwordValidatorEnumerator;
	private ISurveyPersistence surveyDB;
	private SystemConfig()
	{
		passwordEncryption = new BCryptPasswordEncryption();
		userDB = new UserDB();
		databaseConfiguration = new DefaultDatabaseConfiguration();
		courseDB = new CourseDB();
		courseUserRelationshipDB = new CourseUserRelationshipDB();
		questionDB = new QuestionDB();

		validatorDB = new PasswordValidatorDB();
		logger = new LoggerUtil();



		surveyDB = new SurveyDB();
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
	
	public LoggerUtil getLogger() {
		return logger;
	}



	
	public void setLogger(LoggerUtil logger) {
		this.logger = logger;
	}



	
	public ISurveyPersistence getSurveyDB()
	{
		return surveyDB;
	}

	
	public void setSurveyDB(ISurveyPersistence surveyDB)
	{
		this.surveyDB = surveyDB;
	}
}
