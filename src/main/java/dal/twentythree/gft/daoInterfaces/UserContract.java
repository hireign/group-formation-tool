package dal.twentythree.gft.daoInterfaces;

import dal.twentythree.gft.dao.User;

public interface UserContract {
	public void loadUserByBannerID(String bannerID, User user);
	public boolean createUser(User user);
	public boolean updateUser(User user);

}
