package dal.twentythree.gft.repository;

import java.util.List;

import dal.twentythree.gft.dao.User;
import dal.twentythree.gft.exception.CopyCatMeDBConfigException;
import dal.twentythree.gft.exception.CourseGroupFormationException;

public interface UsersDatabaseRepository {
	
	public void createUser(User user) throws CopyCatMeDBConfigException, CourseGroupFormationException;
	
	public List<User> getAllUsers() throws CopyCatMeDBConfigException, CourseGroupFormationException;
	
	public User fetchByBannerId(String bannerId) throws CopyCatMeDBConfigException, CourseGroupFormationException;
}
