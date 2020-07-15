package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.TestSystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.UserAbstractFactory;

public class CurrentUserMock 
{
	
	private UserAbstractFactory userFactory = UserAbstractFactory.getFactory();
	
	public IUser getCurrentAuthenticatedUser() throws Exception 
	{
		IUserPersistence userDB = TestSystemConfig.instance().getUserDB();
		String bannerID = "B00000000";
		IUser u = userFactory.createUser();
		userDB.loadUserByBannerID(bannerID, u);
		return u;
	}

}
