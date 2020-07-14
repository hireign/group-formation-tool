package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;

public class CurrentUserMock 
{
	public IUser getCurrentAuthenticatedUser() throws Exception 
	{
		IUserPersistence userDB = new UserDBMock();
		String bannerID = "B00000000";
		IUser u = new User();
		userDB.loadUserByBannerID(bannerID, u);
		return u;
	}

}
