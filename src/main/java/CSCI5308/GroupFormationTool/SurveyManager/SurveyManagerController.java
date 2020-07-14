package CSCI5308.GroupFormationTool.SurveyManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.QuestionManager.Question;

@Controller
public class SurveyManagerController {
	private static final String CourseID = "courseID";
	private static final String QuestionID = "questionID";
	private ISurveyPersistence surveyDB;
	private Survey currentSurvey = null;

	public SurveyManagerController() {
		surveyDB = SystemConfig.instance().getSurveyDB();
		currentSurvey = new Survey();
	}

	@RequestMapping("/survey")
	public String questionsByDate(Model model, @RequestParam(name = CourseID) String courseID) {
		currentSurvey.load(surveyDB, Long.valueOf(courseID));
		if(currentSurvey.getActive() == 0) {
			model.addAttribute("errorMessage", "Survey is not active or unavailable");
			return "course/course";
		}
		return "redirect:/survey/submit";
	}

	@GetMapping(value = "/survey/submit")
	public String displayQuestion(Model model) {
		model.addAttribute("response", new Response());
		model.addAttribute("question", currentSurvey.getNextQuestion());
		
		if (currentSurvey.getIndex() > currentSurvey.getQuestions().size()-1) {
			model.addAttribute("lastquestion", true);
		} else {
			model.addAttribute("lastquestion", false);
		}
		
		return "survey/displayquestion";
	}

	@RequestMapping(value = "/survey/submit", params = { "nextQuestion" })
	public String displayNextQuestion(Model model, @RequestParam(name = QuestionID) String questionID,
			@ModelAttribute Response response) {
		Question currentQuestion = null;
		try {
			response.setQuestionID(Long.valueOf(questionID));
			response.setSurveyID(currentSurvey.getId());
			response.setUserID(CurrentUser.instance().getCurrentAuthenticatedUser().getId());
			response.save(surveyDB);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Unable to fetch questions, please try again later");
		}
		if (currentSurvey.getIndex() > currentSurvey.getQuestions().size()-1) {
			model.addAttribute("lastquestion", true);
		} else {
			model.addAttribute("lastquestion", false);
		}

		currentQuestion = currentSurvey.getNextQuestion();
		model.addAttribute("response", response);
		model.addAttribute("question", currentQuestion);

		return "survey/displayquestion";
	}

	@RequestMapping("/survey/submit")
	public String storeSurveyResponse(Model model, @RequestParam(name = QuestionID) String questionID,
			@ModelAttribute Response response) {
		try {
			response.setQuestionID(Long.valueOf(questionID));
			response.setSurveyID(currentSurvey.getId());
			response.setUserID(CurrentUser.instance().getCurrentAuthenticatedUser().getId());
			response.save(surveyDB);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Unable to submit survey, please try again later");
		}

		return "index";
	}
}
