package controller;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dao.User;
import exception.CopyCatMeDBConfigException;
import exception.CourseGroupFormationException;
import repository.UsersDatabaseRepository;
import repository.impl.UsersDatabaseRepositoryImpl;
import service.impl.UserValidator;
import util.LoggerUtil;


@Controller
public class UserController {
	
	private final String USERNAME = "username";
	private final String PASSWORD = "password";
	private final String PASSWORD_CONFIRMATION = "passwordConfirmation";
	private final String FIRST_NAME = "firstName";
	private final String LAST_NAME = "lastName";
	private final String EMAIL = "email";
	
	UsersDatabaseRepository userDBImpl = new UsersDatabaseRepositoryImpl();
	Logger myLogger = LoggerUtil.getLoggerInstance(this.getClass());
	
	@GetMapping("/ping")
	public String ping() {
		return "I'm alive!";
	}
	
	@RequestMapping( value = "/", method = RequestMethod.GET)
	public String homePage() {
		return "index";
	}
	
	@RequestMapping( value = "/signup", method = RequestMethod.GET)
	public String displaySignup()
	{
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView createUser(
		   	@RequestParam(name = USERNAME) String bannerID,
		   	@RequestParam(name = PASSWORD) String password,
		   	@RequestParam(name = PASSWORD_CONFIRMATION) String passwordConfirm,
		   	@RequestParam(name = FIRST_NAME) String firstName,
		   	@RequestParam(name = LAST_NAME) String lastName,
		   	@RequestParam(name = EMAIL) String email) throws CopyCatMeDBConfigException, CourseGroupFormationException {
		
		User user = new User();
		user.setBannerId(bannerID);
		user.setEmailId(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(password);
		ModelAndView m;
		if(UserValidator.validateUser(user) && password.equals(passwordConfirm)) {
			userDBImpl.createUser(user);
			m = new ModelAndView("login");
		}
		else {
			m = new ModelAndView("signup");
			m.addObject("errorMessage", "Invalid data, please check your input values.");
		}
		return m;
	}
	
	@RequestMapping( value = "/login", method = RequestMethod.GET)
	public String displayLogIn()
	{
		return "login";
	}
}
