package CSCI5308.GroupFormationTool.AccessControl;

public class UserFactory extends UserAbstractFactory {

	public IUserPersistence makeUserDB() {
		return new UserDB();
	}

	@Override
	public IUser makeUser() {
		return new User();
	}

	@Override
	public IUser makeUser(Long bannerID, IUserPersistence userDB) throws Exception {
		return new User(bannerID, userDB);
	}

	@Override
	public IUser makeUser(String bannerID, IUserPersistence userDB) throws Exception {
		return new User(bannerID, userDB);
	}

}
