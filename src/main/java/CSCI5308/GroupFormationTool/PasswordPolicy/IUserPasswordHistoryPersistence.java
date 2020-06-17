package CSCI5308.GroupFormationTool.PasswordPolicy;

import java.util.ArrayList;

public interface IUserPasswordHistoryPersistence {
	public ArrayList<String> checkPasswordExistsWRTLimit(long id, int limit );
}
