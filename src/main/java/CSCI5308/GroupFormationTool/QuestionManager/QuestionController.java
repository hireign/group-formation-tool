package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;

@Controller
public class QuestionController {

	private static final String ID = "id";
	private static final String instructorID = "instructorID";
	private static final String questionID = "questionID";

	@GetMapping("/course/question/create")
	public String returnCreateQuestionView(Model model, @RequestParam(name = ID) long courseID) {
		ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
		Course course = new Course();
		courseDB.loadCourseByID(courseID, course);
		model.addAttribute("course", course);
		return "course/questionmanager";
	}

	@PostMapping("/course/question/create")
	public String createQuestion(Model model, HttpServletRequest request, @RequestParam(name = ID) long courseID) {
		IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
		Question question = new Question();
		String questionTitle = (String) request.getParameter("questionTitle");
		String questionText = (String) request.getParameter("questionText");
		String questionType = (String) request.getParameter("questionType");
		Map<String, String[]> requestParameterMap = request.getParameterMap();
        List<Option> options = new ArrayList<Option>();
        for(String key : requestParameterMap.keySet()){
            if (key.contains("btn")) {
            	Option option = new Option();
            	option.setText((String) requestParameterMap.get(key)[0]);
            	option.setValue(0);
            	options.add(option);
            }
        }
        question.setTitle(questionTitle);
        question.setText(questionText);
        question.setType(questionType);
        question.setOptions(options);
        question.create(questionDB);
        return "redirect:/index";
	}
	@GetMapping("/questionmanager/questiondirectory")
	public String questionDirectory(Model model) {
		IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
		User currentUser = CurrentUser.instance().getCurrentAuthenticatedUser();
		List<Question> questions = new ArrayList<>();
		questions = questionDB.loadAllQuestionsByInstructor(currentUser.getId());
		model.addAttribute("questions", questions);
		return "questionmanager/questiondirectory";
	}
	@GetMapping("/questionmanager/deletequestion")
	public String deleteQuestionById(@RequestParam(name = questionID) long questionId, Model model) {
		IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
		Question question = new Question();
		question.setId(questionId);
		question.delete(questionDB);
		return "redirect:/questionmanager/questiondirectory";
	}
}
