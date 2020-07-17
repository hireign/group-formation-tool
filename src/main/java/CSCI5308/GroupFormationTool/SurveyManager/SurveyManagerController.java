package CSCI5308.GroupFormationTool.SurveyManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.Courses.CourseFactory;
import CSCI5308.GroupFormationTool.Courses.ICourseUserRelationshipPersistence;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;

@Controller
public class SurveyManagerController {
	private static final String CourseID = "courseID";
	private static final String QuestionID = "questionID";
	private ISurveyPersistence surveyDB;
	private SurveyIterator currentSurvey = null;
	private ICourseUserRelationshipPersistence courseUserRelationshipDB = null;

	public SurveyManagerController() {
		surveyDB = SystemConfig.instance().getSurveyDB();
		currentSurvey = SurveyAbstractFactory.getFactory().makeSurveyIterator();
		courseUserRelationshipDB = CourseFactory.getFactory().makeCourseUserPersistenceDB();
	}

	@RequestMapping("/survey")
	public String loadSurvey(Model model, @RequestParam(name = CourseID) String courseID) {
		try {
			currentSurvey.load(surveyDB, Long.valueOf(courseID), courseUserRelationshipDB,
					CurrentUser.instance().getCurrentAuthenticatedUser());
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Unable to load survey at this moment. Please try again later.");
			return "course/course";
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

		model.addAttribute("response", SurveyAbstractFactory.getFactory().makeResponse());
		model.addAttribute("question", currentSurvey.next());

		return "survey/displayquestion";
	}

	@RequestMapping(value = "/survey/submit", params = { "nextQuestion" })
	public String displayNextQuestion(Model model, @RequestParam(name = QuestionID) String questionID,
									  @ModelAttribute Response response) {
		IQuestion currentQuestion = null;
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

		currentQuestion = currentSurvey.next();
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

}
