package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CSCI5308.GroupFormationTool.SystemConfig;

@Controller
public class QuestionManagerController 
{
	private static final String BannerID = "bannerID";
	private IQuestionPersistence questionDB;
	
	public QuestionManagerController() 
	{
		questionDB = SystemConfig.instance().getQuestionDB();
	}

	@RequestMapping("/question/questionmanager/title")
	public String questionsByTitle(Model model)
	{
	
		List<IQuestion> questionList;
		try {
			questionList = questionDB.loadQuestionsSortedByTitle(CurrentUser.instance().getCurrentAuthenticatedUser().getBannerID());
			model.addAttribute("questionList", questionList);
		} catch (Exception e) {
			model.addAttribute("errorMessage","Unable to fetch questions, please try again later");
		}
		if(model.containsAttribute("errorMessage"));
		model.addAttribute("errorMessage",model.getAttribute("errorMessage"));
		return "question/questions";
	}
	
	@RequestMapping("/question/questionmanager/date")
	public String questionsByDate(Model model, @RequestParam(name = BannerID) String bannerID) 
	{
		List<IQuestion> questionList;
		try {
			questionList = questionDB.loadSortedQuestionsSortedByDate(bannerID);
			model.addAttribute("questionList", questionList);
		} catch (Exception e) {
			model.addAttribute("errorMessage","Unable to fetch questions, please try again later");
		}
		
		return "question/questions";
	}
	
}
