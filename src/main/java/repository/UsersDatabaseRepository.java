package repository;

import java.util.List;

import dao.User;
import exception.CopyCatMeDBConfigException;
import exception.CourseGroupFormationException;
import util.IPasswordEncryption;

public interface UsersDatabaseRepository {
	
	public boolean createUser(User user, IPasswordEncryption passwordEncryption, boolean SignUp) throws CopyCatMeDBConfigException, CourseGroupFormationException;
	
	public List<User> getAllUsers() throws CopyCatMeDBConfigException, CourseGroupFormationException;
	
	public User fetchByBannerId(String bannerId) throws CopyCatMeDBConfigException, CourseGroupFormationException;
}
