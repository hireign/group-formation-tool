package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import CSCI5308.GroupFormationTool.SystemConfig;

@Controller
public class QuestionAdminController 
{
	private static final String ID = "id";
	private static final String BannerID = "bannerID";
	private static final String CourseID = "courseID";
	private static final String QuestionID = "questionID";
	private IQuestionPersistence questionDB;
	
	public QuestionAdminController() 
	{
		questionDB = SystemConfig.instance().getQuestionDB();
	}
	
	@RequestMapping("/question/delete")
	public ModelAndView deleteQuestion(Model model,  @RequestParam(name = ID) long questionId,
										@RequestParam(name = BannerID) String bannerId) 
	{
		ModelAndView mav = new ModelAndView("redirect:/question/questionmanager/title?bannerID="+bannerId);
		try {
			questionDB.deleteQuestionByQuestionId(questionId);
		} catch (Exception e) {
			mav.addObject("errorMessage", "unable to delete question, try again later.");
		}
		
		return mav;
	}
	
	@RequestMapping("/question/add") 
	public String addQuestion(Model model) 
	{
		IQuestion question = QuestionAbstractFactory.getFactory().createQuestion();
		List<QuestionType> questionType = new ArrayList<QuestionType>();
		questionType = Arrays.asList(QuestionType.values());
		model.addAttribute("question", question);
		model.addAttribute("questionTypes",questionType);
		return "/question/addquestion";
	}

	@RequestMapping("/question/reviewQuestion")
	public ModelAndView addOptions(Model model,@RequestParam(name = BannerID) String bannerId, 
											@ModelAttribute Question question) 
	{	
		IOptions options = QuestionAbstractFactory.getFactory().createOptions();
		options.addOption();
		ModelAndView mav = new ModelAndView();
		mav.addObject("question", question);
		mav.addObject("options", options);
		mav.setViewName("/question/reviewquestion");
		return mav;
	}
	
	@RequestMapping("/question/submit")
    public ModelAndView saveQuestion(Model model,@ModelAttribute Question question, @ModelAttribute Options options, 
    										@RequestParam(name = BannerID) String bannerId) 
	{
		long questionID;
		ModelAndView mav = new ModelAndView("redirect:/question/questionmanager/title?bannerID="+bannerId);
		try {
			questionID = question.createQuestion(questionDB, bannerId);
			options.saveOptions(questionDB, questionID);
		} catch (Exception e) {
			mav.addObject("errorMessage", "unable to create question, try again later.");
		}
		
		return mav;
    }
	
	@RequestMapping(value = "/question/submit", params = {"addOptionRow"})
	public ModelAndView addOptionRow(@ModelAttribute Question question, @ModelAttribute Options options, 
											final BindingResult bindingResult) 
    {
		options.addOption();
		ModelAndView mav = new ModelAndView();
		mav.addObject("question", question);
		mav.addObject("options", options);
		mav.setViewName("/question/reviewquestion");
		return mav;
    }

	@RequestMapping("/question/addtosurvey")
	public String addSurveyQuestion(Model model,
									@RequestParam(name = CourseID) String courseId,
									@RequestParam(name = QuestionID) String questionId)
	{
		try {
			System.out.println("printing = "+Long.valueOf(questionId));
			System.out.println("printing = "+Long.valueOf(courseId));
			System.out.println("printing = "+CurrentUser.instance().getCurrentAuthenticatedUser().getID());
			questionDB.addSurveyQuestion(Long.valueOf(questionId),Long.valueOf(courseId),CurrentUser.instance().getCurrentAuthenticatedUser().getID());
		}
		catch (Exception e) {
			model.addAttribute("errorMessage","Unable to add question to survey, please try later");
		}
		return "redirect:/surveymanager?courseID="+courseId+"";
	}



}
