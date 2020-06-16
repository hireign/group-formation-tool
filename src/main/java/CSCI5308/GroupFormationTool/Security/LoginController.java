package CSCI5308.GroupFormationTool.Security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;

@Controller
public class LoginController
{
	@GetMapping("/login")
	public String login(HttpServletRequest request, Model model)
	{
		if(null != CurrentUser.instance().getCurrentAuthenticatedUser()) {
			return "redirect:/index";
		}
		return "login.html";
	}
	
	@GetMapping("/login-error")
	public String loginError(Model model)
	{
		return "login-error.html";
	}
	
	@GetMapping("/logout")
	public String logout()
	{
		CurrentUser.deleteInstance();
		return "login.html?logout";
	}
}