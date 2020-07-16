package CSCI5308.GroupFormationTool.GroupFormation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.Courses.CourseFactory;
import CSCI5308.GroupFormationTool.Courses.ICourseUserRelationshipPersistence;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;
import CSCI5308.GroupFormationTool.QuestionManager.Question;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionsList;
import CSCI5308.GroupFormationTool.SurveyManager.ISurvey;
import CSCI5308.GroupFormationTool.SurveyManager.ISurveyPersistence;
import CSCI5308.GroupFormationTool.SurveyManager.SurveyAbstractFactory;
import CSCI5308.GroupFormationTool.SurveyManager.SurveyIterator;

@Controller
public class CriteriaController {

	private static final String ID = "id";
	private static final String BannerID = "bannerID";
	
	private ISurveyPersistence surveyDB;
	private ICourseUserRelationshipPersistence courseUserRelationshipDB = null;
	private SurveyIterator currentSurvey = null;
	
	public CriteriaController() 
	{
		surveyDB = SystemConfig.instance().getSurveyDB();
		courseUserRelationshipDB = CourseFactory.getFactory().createCourseUserPersistenceDB();
		currentSurvey = SurveyAbstractFactory.getFactory().createSurveyIterator();
	}
	

	
	@GetMapping("/groupformation/criteria")
	public String login(Model model,  @RequestParam(name = ID) long courseID,
			@RequestParam(name = BannerID) String bannerID)
	{
		try {
			GroupingStrategy[] groupingStrategies = GroupingStrategy.values();
			QuestionsList questions = new QuestionsList();
			currentSurvey.load(surveyDB, courseID, courseUserRelationshipDB, CurrentUser.instance().getCurrentAuthenticatedUser());
			for(IQuestion question : currentSurvey.getQuestions()) {
				questions.addQuestion((Question)question);
			}
			model.addAttribute("questions", questions);    
			model.addAttribute("groupingStrategies", groupingStrategies);
			model.addAttribute("courseID", courseID);
			model.addAttribute(BannerID, bannerID);
		} catch (Exception e) {
			model.addAttribute("errorMessage","Unable to fetch Survey Details, please try again later");
		}
		return "groupformation/gfCriteria";
	}
	
}

