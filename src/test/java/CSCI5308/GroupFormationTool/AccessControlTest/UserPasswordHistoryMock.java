package CSCI5308.GroupFormationTool.AccessControlTest;

import java.util.ArrayList;

import CSCI5308.GroupFormationTool.PasswordPolicy.IUserPasswordHistoryPersistence;

public class UserPasswordHistoryMock implements IUserPasswordHistoryPersistence{

	@Override
	public ArrayList<String> checkPasswordExistsWRTLimit(long id, int limit) {
		ArrayList<String> response = new ArrayList<String>();
		response.add("$2a$10$tR3rdVYM/PHkDx5LFaOhue6z7mlZ2vj6IzRc/s1CG/WQB5UyWBVki");
		
		return response;
		
	}

}
