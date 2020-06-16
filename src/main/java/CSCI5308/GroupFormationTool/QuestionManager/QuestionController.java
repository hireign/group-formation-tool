package CSCI5308.GroupFormationTool.QuestionManager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuestionController {

	@GetMapping("/course/question/create")
	public String createQuestion() {
		return "questionmanager";
	}
}
