package CSCI5308.GroupFormationTool.AccessControl;

public abstract class UserAbstractFactory {
	
	private static final UserFactory uFactory = new UserFactory();
	
	public abstract IUserPersistence createUserDB();
	
	public abstract IUser createUser();
	
	public abstract IUser createUser(String bannerID, IUserPersistence userDB ) throws Exception;
	
	public abstract IUser createUser(Long bannerID, IUserPersistence userDB ) throws Exception;
	
	public static UserAbstractFactory getFactory() {
		return uFactory;
	}
}
