package CSCI5308.GroupFormationTool.PasswordPolicy;

import CSCI5308.GroupFormationTool.AccessControl.User;

public interface IPasswordPolicy {
	public String validatePassword(String password, PasswordPolicy passwordPolicy);
	public boolean validatePasswordHistory(User user, String rawPassword, PasswordPolicy passwordPolicy, IUserPasswordHistoryPersistence iUserHist);
}
