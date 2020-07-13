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
	private CurrentSurvey currentSurvey = null;

	public SurveyManagerController() {
		surveyDB = SystemConfig.instance().getSurveyDB();
	}

	@RequestMapping("/survey")
	public String questionsByDate(Model model, @RequestParam(name = CourseID) String courseID) {
		if (currentSurvey == null) {
			currentSurvey = CurrentSurvey.instance();
			currentSurvey.setCourseID(courseID);
			currentSurvey.setSurvey();
		}

		return "redirect:/survey/submit";
	}

	@GetMapping(value = "/survey/submit")
	public String displayQuestion(Model model) {
		Question currentQuestion = currentSurvey.getNextQuestion();
		Response response = new Response();

		model.addAttribute("response", response);
		model.addAttribute("question", currentQuestion);
		model.addAttribute("lastquestion", false);
		return "survey/displayquestion";
	}

	@RequestMapping(value = "/survey/submit", params = { "nextQuestion" })
	public String displayNextQuestion(Model model, @RequestParam(name = QuestionID) String questionID,
			@ModelAttribute Response response) {
		Question currentQuestion = null;
		try {
			response.setQuestionID(Long.valueOf(questionID));
			response.setSurveyID(currentSurvey.getSurvey().getId());
			response.setUserID(CurrentUser.instance().getCurrentAuthenticatedUser().getId());
			response.save(surveyDB);
			currentQuestion = currentSurvey.getNextQuestion();
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Unable to fetch questions, please try again later");
		}
		if (currentSurvey.getIndex() == currentSurvey.getSurvey().getQuestions().size()) {
			model.addAttribute("lastquestion", true);
		} else {
			model.addAttribute("lastquestion", false);
		}

		model.addAttribute("response", response);
		model.addAttribute("question", currentQuestion);

		return "survey/displayquestion";
	}

	@RequestMapping("/survey/submit")
	public String storeSurveyResponse(Model model, @RequestParam(name = QuestionID) String questionID,
			@ModelAttribute Response response) {
		try {
			response.setQuestionID(Long.valueOf(questionID));
			response.setSurveyID(currentSurvey.getSurvey().getId());
			response.setUserID(CurrentUser.instance().getCurrentAuthenticatedUser().getId());
			response.save(surveyDB);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Unable to submit survey, please try again later");
		}

		return "index";
	}
}
