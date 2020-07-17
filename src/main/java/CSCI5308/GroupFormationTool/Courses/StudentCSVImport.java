package CSCI5308.GroupFormationTool.Courses;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.LoggerInterface;
import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.UserAbstractFactory;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;

public class StudentCSVImport implements IStudentCSVImport {
	private List<String> successResults;
	private List<String> failureResults;
	private ICourse course;
	private IUserPersistence userDB;
	private IPasswordEncryption passwordEncryption;
	private IStudentCSVParser parser;
	private LoggerInterface logger = SystemConfig.instance().getLogger();

	public StudentCSVImport(IStudentCSVParser parser, ICourse course) {
		this.course = course;
		successResults = new ArrayList<String>();
		failureResults = new ArrayList<String>();
		userDB = SystemConfig.instance().getUserDB();
		passwordEncryption = SystemConfig.instance().getPasswordEncryption();
		this.parser = parser;
		enrollStudentFromRecord();
	}

	public void enrollStudentFromRecord() {
		List<IUser> studentList = parser.parseCSVFile(failureResults);
		for (IUser u : studentList) {
			String bannerID = u.getBanner();
			String firstName = u.getFirstName();
			String lastName = u.getLastName();
			String email = u.getEmail();
			String userDetails = bannerID + " " + firstName + " " + lastName + " " + email;

			IUser user = UserAbstractFactory.getFactory().makeUser();
			try {
				userDB.loadUserByBannerID(bannerID, user);
			} catch (Exception e1) {
				logger.warn(StudentCSVImport.class.toString(),
						String.format("action=uploadCSV status=failure bannerID=%s", u.getBannerID()));
				failureResults.add("Unable to save this user to DB: " + userDetails);
				return;
			}

			if (user.isInvalidUser()) {
				user.setBannerID(bannerID);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setEmail(email);
				try {
					user.createUser(userDB, passwordEncryption, null);

					successResults.add("Created: " + userDetails);
					userDB.loadUserByBannerID(bannerID, user);
				} catch (Exception e) {
					logger.warn(StudentCSVImport.class.toString(),
							String.format("action=uploadCSV status=failure bannerID=%s", bannerID));
					failureResults.add("Unable to save this user to DB: " + userDetails);
					return;
				}
			}
			try {
				course.enrollUserInCourse(Role.STUDENT, user);
				successResults.add("User enrolled in course: " + userDetails);
			} catch (Exception e) {
				failureResults.add("Unable to enroll user in course: " + userDetails);
			}

		}
	}

	public List<String> getSuccessResults() {
		return successResults;
	}

	public List<String> getFailureResults() {
		return failureResults;
	}

}
