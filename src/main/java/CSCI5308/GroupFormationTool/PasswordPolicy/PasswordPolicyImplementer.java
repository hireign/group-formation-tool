package CSCI5308.GroupFormationTool.PasswordPolicy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;

public class PasswordPolicyImplementer implements IPasswordPolicy {

//	IPasswordPolicyPersistence Passwordpersistence = new PasswordPolicyPopulator();
//	PasswordPolicy passwordPolicy = new PasswordPolicy(Passwordpersistence);

	@Override
	public String validatePassword(String password,PasswordPolicy passwordPolicy) {

		if (passwordPolicy.getMaximum_length() != 0 && password.length() > passwordPolicy.getMaximum_length()) {
			return "Password Length is more than maximum length";
		}
		if (password.length() < passwordPolicy.getMinimum_length()) {
			return "Password Length is less than Minimum length";
		}

		HashSet<Character> disallowedChars = new HashSet<Character>();
		for (int i = 0; i < passwordPolicy.getDisallowed_Chars().length(); i++) {
			disallowedChars.add(passwordPolicy.getDisallowed_Chars().charAt(i));
		}

		int lowerCase = 0;
		int upperCase = 0;
		int specialChars = 0;
		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			if ((int) c > 64 && (int) c < 91) {
				upperCase++;
			}
			else if ((int) c > 96 && (int) c < 123) {
				lowerCase++;
			} else {
				if (disallowedChars.contains(c)) {
					return "Password contains disallowed characters";
				}
				specialChars++;
			}
		}

		if (upperCase < passwordPolicy.getMinimum_uppercase_Chars()) {
			return "Password should contain atleast minimum number of uppercase letters";
		}

		if (lowerCase < passwordPolicy.getMinimum_lowercase_Chars()) {
			return "Password should contain atleast minimum number of lowercase letters";
		}

		if (specialChars < passwordPolicy.getMinimum_special_characters()) {
			return "Password should contain atleast minimum number of special characters";
		}

		return "";
	}

	@Override
	public boolean validatePasswordHistory(User user, String rawPassword, PasswordPolicy passwordPolicy, IUserPasswordHistoryPersistence iUserHist) {
		boolean success = true;
		IPasswordEncryption iPasswordEncryption = SystemConfig.instance().getPasswordEncryption();
		
		int passwordHistoryLimit = passwordPolicy.getPassword_History_Constraint_No();
		ArrayList<String> fetchedPasswords = iUserHist.checkPasswordExistsWRTLimit(user.getId(), passwordHistoryLimit);
		
		Iterator<String> i = fetchedPasswords.iterator();
		while(i.hasNext()) {
			String currentPassword = i.next();
			boolean match = iPasswordEncryption.matches(rawPassword, currentPassword);
			if(match) {
				success = false;
			}
		}
		
		return success;
	}

}
