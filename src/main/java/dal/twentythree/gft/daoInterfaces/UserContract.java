package dal.twentythree.gft.daoInterfaces;

import dal.twentythree.gft.dao.UserContactInfo;

public interface UserContract {
	public void loadUserByBannerID(String bannerID, UserContactInfo user);
	public boolean createUser(UserContactInfo user);
	public boolean updateUser(UserContactInfo user);

}
