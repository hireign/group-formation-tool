package CSCI5308.GroupFormationTool.AccessControl;

import java.util.List;

import CSCI5308.GroupFormationTool.PasswordValidation.IPasswordValidatorEnumerator;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;

public interface IUser {

	void setDefaults();

	void setID(long id);

	long getID();

	void setId(long id);

	long getId();

	void setPassword(String password);

	String getPassword();

	void setBannerID(String bannerID);

	String getBannerID();

	String getBanner();

	void setFirstName(String name);

	String getFirstName();

	void setLastName(String name);

	String getLastName();

	void setEmail(String email);

	String getEmail();

	boolean isInvalidUser();

	void createUser(IUserPersistence userDB, IPasswordValidatorEnumerator passwordEnumerator,
					IPasswordEncryption passwordEncryption, IUserNotifications notification, List<String> errorMessages)
			throws Exception;

	void createUser(IUserPersistence userDB, IPasswordEncryption passwordEncryption, IUserNotifications notification)
			throws Exception;

}