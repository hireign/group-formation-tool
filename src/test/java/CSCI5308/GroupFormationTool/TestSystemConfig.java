package CSCI5308.GroupFormationTool;

import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControlTest.CurrentUserMock;
import CSCI5308.GroupFormationTool.AccessControlTest.UserDBMock;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.Courses.ICourseUserRelationshipPersistence;
import CSCI5308.GroupFormationTool.CoursesTest.CSVReaderMock;
import CSCI5308.GroupFormationTool.CoursesTest.CourseDBMock;
import CSCI5308.GroupFormationTool.CoursesTest.CourseUserRelationshipDBMock;
import CSCI5308.GroupFormationTool.PasswordValidation.IPasswordValidatorPersistence;
import CSCI5308.GroupFormationTool.PasswordValidationTest.PasswordValidatorDBMock;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestionPersistence;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionDBMock;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;
import CSCI5308.GroupFormationTool.SecurityTest.PasswordEncryptionMock;
import CSCI5308.GroupFormationTool.SurveyManager.ISurveyPersistence;
import CSCI5308.GroupFormationTool.SurveyManagerTest.SurveyDBMock;

public class TestSystemConfig {

	private static TestSystemConfig uniqueInstance = null;
	
	private IPasswordEncryption passwordEncryption;
	private IUserPersistence userDB;
	private CSVReaderMock csvReader;
	private ICoursePersistence courseDB;
	private ICourseUserRelationshipPersistence courseUserRelationshipDB;
	private IQuestionPersistence questionDB;
	private IPasswordValidatorPersistence validatorDB;
	private ISurveyPersistence surveyDB;
	private CurrentUserMock currentUser;
	
	private TestSystemConfig() {
		userDB = new UserDBMock();
		courseDB = new CourseDBMock();
		courseUserRelationshipDB = new CourseUserRelationshipDBMock();
		csvReader = new CSVReaderMock();
		validatorDB = new PasswordValidatorDBMock();
		questionDB = new QuestionDBMock();
		passwordEncryption = new PasswordEncryptionMock();
		surveyDB = new SurveyDBMock();
		currentUser = new CurrentUserMock();
	}
	
	public static TestSystemConfig instance()
	{		
		if (null == uniqueInstance)
		{
			uniqueInstance = new TestSystemConfig();
		}
		return uniqueInstance;
	}

	public IPasswordEncryption getPasswordEncryption() {
		return passwordEncryption;
	}

	public void setPasswordEncryption(IPasswordEncryption passwordEncryption) {
		this.passwordEncryption = passwordEncryption;
	}

	public IUserPersistence getUserDB() {
		return userDB;
	}

	public void setUserDB(IUserPersistence userDB) {
		this.userDB = userDB;
	}

	public CSVReaderMock getCsvReader() {
		return csvReader;
	}

	public void setCsvReader(CSVReaderMock csvReader) {
		this.csvReader = csvReader;
	}

	public ICoursePersistence getCourseDB() {
		return courseDB;
	}

	public void setCourseDB(ICoursePersistence courseDB) {
		this.courseDB = courseDB;
	}

	public ICourseUserRelationshipPersistence getCourseUserRelationshipDB() {
		return courseUserRelationshipDB;
	}

	public void setCourseUserRelationshipDB(ICourseUserRelationshipPersistence courseUserRelationshipDB) {
		this.courseUserRelationshipDB = courseUserRelationshipDB;
	}

	public IQuestionPersistence getQuestionDB() {
		return questionDB;
	}

	public void setQuestionDB(IQuestionPersistence questionDB) {
		this.questionDB = questionDB;
	}

	public IPasswordValidatorPersistence getValidatorDB() {
		return validatorDB;
	}

	public void setValidatorDB(IPasswordValidatorPersistence validatorDB) {
		this.validatorDB = validatorDB;
	}

	public ISurveyPersistence getSurveyDB() {
		return surveyDB;
	}

	public void setSurveyDB(ISurveyPersistence surveyDB) {
		this.surveyDB = surveyDB;
	}

	public CurrentUserMock getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(CurrentUserMock currentUser) {
		this.currentUser = currentUser;
	}	
	
}
