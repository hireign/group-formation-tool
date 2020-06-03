package dal.twentythree.gft.repository;

import java.util.List;

import dal.twentythree.gft.dao.UserContactInfo;
import dal.twentythree.gft.exception.CopyCatMeDBConfigException;
import dal.twentythree.gft.exception.CourseGroupFormationException;

public interface UsersDatabaseRepository {
	
	public Long createUser(UserContactInfo user) throws CopyCatMeDBConfigException, CourseGroupFormationException;
	
	public List<UserContactInfo> getAllUsers() throws CopyCatMeDBConfigException, CourseGroupFormationException;
	
	public UserContactInfo fetchByBannerId(String bannerId) throws CopyCatMeDBConfigException, CourseGroupFormationException;
}
