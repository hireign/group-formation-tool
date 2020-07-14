package CSCI5308.GroupFormationTool.AccessControl;

public interface IUserPersistence
{
	public void loadUserByID(long id, IUser user) throws Exception;
	public void loadUserByBannerID(String bannerID, IUser user) throws Exception;
	public void createUser(IUser user) throws Exception;
}
