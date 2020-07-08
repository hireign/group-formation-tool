package CSCI5308.GroupFormationTool.Security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.PasswordValidation.IPasswordValidatorEnumerator;
import CSCI5308.GroupFormationTool.PasswordValidation.IPasswordValidatorPersistence;
import CSCI5308.GroupFormationTool.PasswordValidation.PasswordValidatorEnumerator;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;

@Controller
public class SignupController
{
	private final String USERNAME = "username";
	private final String PASSWORD = "password";
	private final String PASSWORD_CONFIRMATION = "passwordConfirmation";
	private final String FIRST_NAME = "firstName";
	private final String LAST_NAME = "lastName";
	private final String EMAIL = "email";
	private IPasswordValidatorEnumerator passwordValidatorEnumerator;
	
	public SignupController() {
		IPasswordValidatorPersistence validatorDB = SystemConfig.instance().getPasswordValidatorDB();
		passwordValidatorEnumerator = new PasswordValidatorEnumerator(validatorDB);
		SystemConfig.instance().setPasswordValidatorEnumerator(passwordValidatorEnumerator);
	}
	
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
		boolean success = false;
		List<String> errorMessages = new ArrayList<String>();
		if (User.isBannerIDValid(bannerID) &&
			 User.isEmailValid(email) &&
			 User.isFirstNameValid(firstName) &&
			 User.isLastNameValid(lastName) &&
			 password.equals(passwordConfirm))
		{
			User u = new User();
			u.setBannerID(bannerID);
			u.setPassword(password);
			u.setFirstName(firstName);
			u.setLastName(lastName);
			u.setEmail(email);
			IUserPersistence userDB = SystemConfig.instance().getUserDB();
			IPasswordEncryption passwordEncryption = SystemConfig.instance().getPasswordEncryption();
			passwordValidatorEnumerator = SystemConfig.instance().getPasswordValidatorEnumerator();
			success = u.createUser(userDB, passwordValidatorEnumerator, passwordEncryption, null, errorMessages);
		}
		ModelAndView m = new ModelAndView("login");
		if (success == false)
		{
			m = new ModelAndView("signup");
			m.addObject("errorMessage", "Invalid data, please check your values.");
			m.addObject("passwordInvalid",errorMessages);
			System.out.println(errorMessages);
		}
		return m;
	}
}