package model;

import dal.twentythree.gft.dao.*;
import dal.twentythree.gft.daoInterfaces.UserContract;

public class UserDBMock implements UserContract
{

	public void loadUserByBannerID(String bannerID, UserContactInfo user)
	{
		user.setBannerId(bannerID);
		user.setEmailId("shewani@dal.ca");
		user.setFirstName("Rahul");
		user.setLastName("Sanju");
	}
	
	public boolean createUser(UserContactInfo user)
	{
		//TODO
		return true;
	}
	
	public boolean updateUser(UserContactInfo user)
	{
		//TODO
		return true;
	}
}
