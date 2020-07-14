package CSCI5308.GroupFormationTool.SurveyManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SurveyAdminController {
	@GetMapping("/survey/admin/groups")
	public String showGroups(Model model)
	{
		return "index";
	}
}
