package CSCI5308.GroupFormationTool.Security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.PasswordPolicy.IPasswordPolicy;
import CSCI5308.GroupFormationTool.PasswordPolicy.IPasswordPolicyPersistence;
import CSCI5308.GroupFormationTool.PasswordPolicy.IUserPasswordHistoryPersistence;
import CSCI5308.GroupFormationTool.PasswordPolicy.PasswordPolicy;
import CSCI5308.GroupFormationTool.AccessControl.*;

@Controller
public class ChangePasswordController {

	IPasswordPolicyPersistence iPasswordPolicy = SystemConfig.instance().getiPasswordPolicyPersistance();
	IPasswordPolicy passwordPolicy = SystemConfig.instance().getPasswordPolicy();
	PasswordPolicy passwordPolicyObject = new PasswordPolicy(iPasswordPolicy);
	IPasswordEncryption passwordEncryption = SystemConfig.instance().getPasswordEncryption();
	IUserPasswordHistoryPersistence iUserHist = SystemConfig.instance().getiUserPasswordHistory();
	
	private final String PASSWORD = "password";
	
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public ModelAndView processPasswordRequest() {
		ModelAndView m = new ModelAndView("changePassword");
		return m;
		
	}
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST) 
	   public ModelAndView processChangePassword(
	   	@RequestParam(name = PASSWORD) String password)
		{
		User user = CurrentUser.instance().getCurrentAuthenticatedUser();
		boolean passwordPresent = passwordPolicy.validatePasswordHistory(user, password, passwordPolicyObject, iUserHist);
		String error = passwordPolicy.validatePassword(password, passwordPolicyObject);
		
		ModelAndView m;
		if(passwordPresent) {
			m = new ModelAndView("changePassword");
			m.addObject("errorMessage", "Password cannot be the same as last used passwords.");
		}
		else if(error.length() > 0) {
			m = new ModelAndView("changePassword");
			m.addObject("errorMessage", error);
		}
		else {			
			IUserPersistence userDB = SystemConfig.instance().getUserDB();
			user.setPassword(passwordEncryption.encryptPassword(password));
			boolean success = user.UpdateUserPassword(userDB);

			if(success) {
				m = new ModelAndView("login");
				m.addObject("SuccessMessage", "Password Changed Successfully. Please Relogin.");
			}
			else {
				m = new ModelAndView("changePassword");
				m.addObject("errorMessage", "Error while updating password");
			}
		}
		return m;
		}
	
}
