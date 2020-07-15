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
	private ISurvey currentSurvey = null;

	public SurveyManagerController() {
		surveyDB = SystemConfig.instance().getSurveyDB();
		currentSurvey = SurveyAbstractFactory.getFactory().createSurvey();
	}

	@RequestMapping("/survey")
	public String questionsByDate(Model model, @RequestParam(name = CourseID) String courseID) {
		try {
			currentSurvey.load(surveyDB, Long.valueOf(courseID));
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Unable to load survey at this moment. Please try again later.");
		}

		if (currentSurvey.getActive() == 0) {
			model.addAttribute("errorMessage", "Survey is not active or unavailable");
			return "course/course";
		}
		return "redirect:/survey/submit";
	}

	@GetMapping(value = "/survey/submit")
	public String displayQuestion(Model model) {
		int remainingQuestions = currentSurvey.getQuestionSize() - currentSurvey.getIndex();

		if (remainingQuestions < 1) {
			currentSurvey.setIndex(0);
		} else if (remainingQuestions == 1) {
			model.addAttribute("lastquestion", true);
		} else {
			model.addAttribute("lastquestion", false);
		}

		model.addAttribute("response", SurveyAbstractFactory.getFactory().createResponse());
		model.addAttribute("question", currentSurvey.getNextQuestion());

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
			model.addAttribute("errorMessage", "Unable to save last question, please retry");
		}
		
		int remainingQuestions = currentSurvey.getQuestionSize() - currentSurvey.getIndex();
		
		if (remainingQuestions == 1) {
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
			model.addAttribute("errorMessage", "Unable to submit survey, please retry");
		}

		return "redirect:/";
	}

	@GetMapping(value = "/surveymanager")
	public String loadSurvey(Model model, @RequestParam(name = CourseID) String courseID) {
		try {
			currentSurvey.load(surveyDB, Long.valueOf(courseID));
			currentSurvey.setIndex(0);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("courseID", courseID);
		model.addAttribute("questionList", currentSurvey.getQuestions());
		return "survey/createsurvey";
	}

	@RequestMapping("/deletesurveyquestion")
	public String deleteSurveyQuestion(Model model,
			 @RequestParam(name = QuestionID) long questionID,
			 @RequestParam(name = CourseID) long courseID){
		try {
			surveyDB.deleteSurveyQuestion(questionID, courseID);
			currentSurvey.load(surveyDB, courseID);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Unable to delete question, please retry");
		}
		currentSurvey.setIndex(0);
		model.addAttribute("courseID", courseID);
		model.addAttribute("questionList", currentSurvey.getQuestions());
		return "survey/createsurvey";
	}











}
