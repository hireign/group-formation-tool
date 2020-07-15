package CSCI5308.GroupFormationTool.SurveyManager;

import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SurveyAdminController {
	private static final String CourseID = "courseID";
	private static final String QuestionID = "questionID";
	private ISurveyPersistence surveyDB;
	private ISurvey currentSurvey = null;

	public SurveyAdminController() {
		surveyDB = SystemConfig.instance().getSurveyDB();
		currentSurvey = SurveyAbstractFactory.getFactory().createSurvey();
	}

	@GetMapping("/survey/admin/groups")
	public String showGroups(Model model)
	{
		return "index";
	}

	@RequestMapping("/deletesurveyquestion")
	public String deleteSurveyQuestion(Model model,
									   @RequestParam(name = QuestionID) long questionID,
									   @RequestParam(name = CourseID) long courseID){
		try {
			surveyDB.deleteSurveyQuestion(questionID, courseID);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Unable to delete question, please retry");
		}
		try{
			currentSurvey.load(surveyDB, courseID);
		}
		catch (Exception e) {
			model.addAttribute("errorMessage", "Unable to load survey, please retry later");
		}
		currentSurvey.setIndex(0);
		model.addAttribute("courseID", courseID);
		model.addAttribute("questionList", currentSurvey.getQuestions());
		return "survey/createsurvey";
	}

	@GetMapping(value = "/surveymanager")
	public String loadSurvey(Model model, @RequestParam(name = CourseID) String courseID) {
		try {
			currentSurvey.load(surveyDB, Long.valueOf(courseID));
			currentSurvey.setIndex(0);
		}
		catch (Exception e) {
			model.addAttribute("errorMessage", "Unable to load survey, please retry");
		}
		model.addAttribute("courseID", courseID);
		model.addAttribute("questionList", currentSurvey.getQuestions());
		return "survey/createsurvey";
	}

	@RequestMapping("/question/addtosurvey")
	public String addSurveyQuestion(Model model,
									@RequestParam(name = CourseID) String courseId,
									@RequestParam(name = QuestionID) String questionId)
	{
		try {
			surveyDB.addSurveyQuestion(Long.valueOf(questionId),Long.valueOf(courseId),CurrentUser.instance().getCurrentAuthenticatedUser().getID());
		}
		catch (Exception e) {
			model.addAttribute("errorMessage","Unable to add question to survey, please try later");
		}
		return "redirect:/surveymanager?courseID="+courseId+"";
	}

}

