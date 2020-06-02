package dal.twentythree.gft.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dal.twentythree.gft.dao.User;

public class UserValidator {

	private static final String REGEX_EMAIL = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
	
	public static boolean validateUser(User user) {
		boolean success = false;
		if (isBannerIDValid(user.getBannerId()) &&
			isEmailValid(user.getEmailId()) &&
			isFirstNameValid(user.getFirstName()) &&
			isLastNameValid(user.getLastName()))
		success = true;
		return success;
	}
	
	private static boolean isStringNullOrEmpty(String s)
	{
		if (null == s)
		{
			return true;
		}
		return s.isEmpty();
	}
	
	public static boolean isBannerIDValid(String bannerID)
	{
		return !isStringNullOrEmpty(bannerID);
	}
		
	public static boolean isFirstNameValid(String name)
	{
		return !isStringNullOrEmpty(name);
	}
	
	public static boolean isLastNameValid(String name)
	{
		return !isStringNullOrEmpty(name);
	}
	
	public static boolean isEmailValid(String email)
	{
		if (isStringNullOrEmpty(email))
		{
			return false;
		}
		 
		Pattern pattern = Pattern.compile(REGEX_EMAIL);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

}
