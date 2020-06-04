package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dao.Course;
import dao.User;
import dao.UserAccountStatus;
import dao.UserLogin;
import exception.CopyCatMeDBConfigException;
import exception.CourseGroupFormationException;
import repository.UsersDatabaseRepository;
import repository.impl.UsersDatabaseRepositoryImpl;
import service.impl.EmailService;
import service.impl.UserValidator;
import util.BCryptPasswordEncryption;
import util.IPasswordEncryption;
import util.LoggerUtil;

@Controller
@ComponentScan({ "service.impl" })
public class UserController {

	private final String USERNAME = "username";
	private final String PASSWORD = "password";
	private final String PASSWORD_CONFIRMATION = "passwordConfirmation";
	private final String FIRST_NAME = "firstName";
	private final String LAST_NAME = "lastName";
	private final String EMAIL = "email";

	UsersDatabaseRepository userDBImpl = new UsersDatabaseRepositoryImpl();
	Logger myLogger = LoggerUtil.getLoggerInstance(this.getClass());
	IPasswordEncryption passwordEncryption = new BCryptPasswordEncryption();
	@Autowired
	EmailService emailService;

	@GetMapping("/ping")
	public String ping() {
		return "I'm alive!";
	}

	@GetMapping({ "/", "/index" })
	public String homePage(HttpSession session) {
		String username = (String) session.getAttribute("USER");
//		HashMap<Course, String> course = (HashMap<Course, String>) session.getAttribute("COURSES");
		ArrayList<Course> course = (ArrayList<Course>) session.getAttribute("COURSES");
		if (course == null && username != null) {
			HashMap<Course, String> courseMap = userDBImpl.getRegisteredCourses(username);
			ArrayList<String> cL = new ArrayList<String>();
			if (courseMap != null) {
				for (Map.Entry<Course, String> e : courseMap.entrySet()) {
					Course key = e.getKey();
					cL.add("CSCI" + key.getCourseId() + ": " + key.getCourseName());
					session.setAttribute("COURSES", cL);
//					session.setAttribute("COURSES", courseMap);
				}
			}
		}
		return "index";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String displaySignup(HttpSession session) {
		if (session.getAttribute("USER") != null) {
			return "index";
		}
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView createUser(HttpServletRequest request, @RequestParam(name = USERNAME) String bannerID,
			@RequestParam(name = PASSWORD) String password,
			@RequestParam(name = PASSWORD_CONFIRMATION) String passwordConfirm,
			@RequestParam(name = FIRST_NAME) String firstName, @RequestParam(name = LAST_NAME) String lastName,
			@RequestParam(name = EMAIL) String email) throws CopyCatMeDBConfigException, CourseGroupFormationException {

		User user = new User();
		user.setBannerId(bannerID);
		user.setEmailId(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(password);
		ModelAndView m = null;
		if (UserValidator.validateUser(user) && password.equals(passwordConfirm)) {
			userDBImpl.createUser(user, passwordEncryption, false);

			// -- Send Confirmation Email
			UserAccountStatus uas = new UserAccountStatus(email, UUID.randomUUID().toString());
			String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getLocalPort();
			if (sendConfirmEmail(uas, appUrl)) {
				userDBImpl.saveAccountStatus(uas);
				m = new ModelAndView("login");
				m.addObject("message", "Please confirm the account by clicking on the link in your email at " + email);
			}
		} else {
			m = new ModelAndView("signup");
			m.addObject("errorMessage", "Invalid data, please check your input values.");
		}
		return m;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String displayLogIn(HttpSession session, HttpServletResponse response) {
		if (session.getAttribute("USER") != null) {
			try {
				response.sendRedirect("/index");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "login";
	}

	@PostMapping("/login")
	public void processLogin(HttpSession session, HttpServletResponse response,
			@RequestParam(name = USERNAME) String bannerID, @RequestParam(name = PASSWORD) String password)
			throws IOException {
		ModelAndView m = null;
		UserLogin userLogin = new UserLogin(bannerID, password);

		if (session.getAttribute("USER") != null) {
			response.sendRedirect("/index");
			return;
		}

		if (UserValidator.validateUserLogin(userLogin) && userDBImpl.getUserLogin(userLogin)) {
			HashMap<String, String> sessionMap = new HashMap<String, String>();
			sessionMap.put("USER", bannerID);
			sessionMap.put("ROLE", userDBImpl.getUserRole(bannerID));
			createSession(session, sessionMap);
			response.sendRedirect("/index");
			return;
		} else {
			response.sendRedirect("/login?error");
			return;
		}

	}

	@GetMapping("/logout")
	public ModelAndView processLogout(HttpSession session) {
		session.invalidate();
		ModelAndView m = new ModelAndView("login");
		m.addObject("message", "You've logged out successfully");
		return m;
	}

	@GetMapping("/confirm")
	public ModelAndView verifyUser(@RequestParam("token") String token) {
		ModelAndView m = new ModelAndView("login");
		if (userDBImpl.verifyUser(token, 1)) {
			m.addObject("message", "Email ID successfully confirmed. Please sign-in");
			return m;
		} else {
			m.addObject("error", "Invalid token");
			return m;
		}
	}

	private boolean sendConfirmEmail(UserAccountStatus uas, String appUrl) {
		String emailBody = "Please click below link to confirm your email address\n" + appUrl + "/confirm?token="
				+ uas.getConfirmationToken();
		try {
			SimpleMailMessage registrationEmail = new SimpleMailMessage();
			registrationEmail.setTo(uas.getEmail());
			registrationEmail.setSubject("Registration Confirmation");
			registrationEmail.setText(emailBody);
			emailService.sendEmail(registrationEmail);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private void createSession(HttpSession session, HashMap<String, String> sessionMap) {
		for (Map.Entry<String, String> sessVar : sessionMap.entrySet()) {
			session.setAttribute(sessVar.getKey(), sessVar.getValue());
		}
		return;
	}
}
