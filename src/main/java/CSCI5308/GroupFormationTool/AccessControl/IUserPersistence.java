package CSCI5308.GroupFormationTool.AccessControl;

public interface IUserPersistence
{
	public void loadUserByID(long id, User user) throws Exception;
	public void loadUserByBannerID(String bannerID, User user) throws Exception;
	public void createUser(User user) throws Exception;
}
