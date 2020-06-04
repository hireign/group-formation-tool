package repository;

import java.util.List;

import dao.User;
import dao.UserAccountStatus;
import dao.UserLogin;
import exception.CopyCatMeDBConfigException;
import exception.CourseGroupFormationException;
import util.IPasswordEncryption;

public interface UsersDatabaseRepository {
	
	public boolean createUser(User user, IPasswordEncryption passwordEncryption, boolean SignUp) throws CopyCatMeDBConfigException, CourseGroupFormationException;
	
	public List<User> getAllUsers() throws CopyCatMeDBConfigException, CourseGroupFormationException;
	
	public User fetchByBannerId(String bannerId) throws CopyCatMeDBConfigException, CourseGroupFormationException;
	
	public boolean getUserLogin(UserLogin userLogin);

	public String getUserRole(String username);

	public void saveAccountStatus(UserAccountStatus uas);

	boolean verifyUser(String token, int i);
}
