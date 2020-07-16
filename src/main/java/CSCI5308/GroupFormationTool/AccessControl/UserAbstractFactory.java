package CSCI5308.GroupFormationTool.AccessControl;

public abstract class UserAbstractFactory {

	private static final UserFactory uFactory = new UserFactory();

	public abstract IUserPersistence makeUserDB();

	public abstract IUser makeUser();

	public abstract IUser makeUser(String bannerID, IUserPersistence userDB) throws Exception;

	public abstract IUser makeUser(Long bannerID, IUserPersistence userDB) throws Exception;

	public static UserAbstractFactory getFactory() {
		return uFactory;
	}
}
