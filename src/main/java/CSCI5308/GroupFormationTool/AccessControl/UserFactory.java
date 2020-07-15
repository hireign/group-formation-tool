package CSCI5308.GroupFormationTool.AccessControl;

public class UserFactory extends UserAbstractFactory {

	public IUserPersistence createUserDB() {
		return new UserDB();
	}

	@Override
	public IUser createUser() {
		return new User();
	}

	@Override
	public IUser createUser(String bannerID, IUserPersistence userDB) throws Exception {
		return new User(bannerID, userDB);
	}

}
