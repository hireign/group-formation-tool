package model;

import dao.*;
import daoInterfaces.UserContract;

public class UserDBMock implements UserContract
{

	public void loadUserByBannerID(String bannerID, User user)
	{
		user.setBannerId(bannerID);
		user.setEmailId("shewani@dal.ca");
		user.setFirstName("Rahul");
		user.setLastName("Sanju");
	}
	
	public boolean createUser(User user)
	{
		//TODO
		return true;
	}
	
	public boolean updateUser(User user)
	{
		//TODO
		return true;
	}
}
