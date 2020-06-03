package repository;

import java.util.List;

import dao.User;
import exception.CopyCatMeDBConfigException;
import exception.CourseGroupFormationException;

public interface UsersDatabaseRepository {
	
	public void createUser(User user) throws CopyCatMeDBConfigException, CourseGroupFormationException;
	
	public List<User> getAllUsers() throws CopyCatMeDBConfigException, CourseGroupFormationException;
	
	public User fetchByBannerId(String bannerId) throws CopyCatMeDBConfigException, CourseGroupFormationException;
}
