package CSCI5308.GroupFormationTool.Security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.PasswordPolicy.IPasswordPolicy;
import CSCI5308.GroupFormationTool.PasswordPolicy.IPasswordPolicyPersistence;
import CSCI5308.GroupFormationTool.PasswordPolicy.PasswordPolicy;

@Controller
public class SignupController
{
	private final String USERNAME = "username";
	private final String PASSWORD = "password";
	private final String PASSWORD_CONFIRMATION = "passwordConfirmation";
	private final String FIRST_NAME = "firstName";
	private final String LAST_NAME = "lastName";
	private final String EMAIL = "email";
	IPasswordPolicyPersistence iPasswordPolicy = SystemConfig.instance().getiPasswordPolicyPersistance();
	IPasswordPolicy passwordPolicy = SystemConfig.instance().getPasswordPolicy();
	IPasswordEncryption passwordEncryption = SystemConfig.instance().getPasswordEncryption();
	PasswordPolicy passwordPolicyObject = new PasswordPolicy(iPasswordPolicy);
	
	
	@GetMapping("/signup")
	public String displaySignup(Model model)
	{
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST) 
   public ModelAndView processSignup(
   	@RequestParam(name = USERNAME) String bannerID,
   	@RequestParam(name = PASSWORD) String password,
   	@RequestParam(name = PASSWORD_CONFIRMATION) String passwordConfirm,
   	@RequestParam(name = FIRST_NAME) String firstName,
   	@RequestParam(name = LAST_NAME) String lastName,
   	@RequestParam(name = EMAIL) String email)
	{	
		
		String error = passwordPolicy.validatePassword(password, passwordPolicyObject);
		
		boolean success = false;
		if (User.isBannerIDValid(bannerID) &&
			 User.isEmailValid(email) &&
			 User.isFirstNameValid(firstName) &&
			 User.isLastNameValid(lastName) &&
			 error.isEmpty() &&
			 password.equals(passwordConfirm))
		{
			User u = new User();
			u.setBannerID(bannerID);
			u.setPassword(password);
			u.setFirstName(firstName);
			u.setLastName(lastName);
			u.setEmail(email);
			IUserPersistence userDB = SystemConfig.instance().getUserDB();
			success = u.createUser(userDB, passwordEncryption, null);
		}
		ModelAndView m;
		if (success)
		{
			// This is lame, I will improve this with auto-signin for M2.
			m = new ModelAndView("login");
		}
		else
		{
			// Something wrong with the input data.
			m = new ModelAndView("signup");
			if(error.isEmpty()) {
				m.addObject("errorMessage", "Invalid data, please check your values.");
			}
			else {
				m.addObject("errorMessage", error);
			}
			
		}
		return m;
	}
}