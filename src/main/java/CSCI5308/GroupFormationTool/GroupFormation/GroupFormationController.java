package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.AccessControl.UserAbstractFactory;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionsList;
import CSCI5308.GroupFormationTool.SurveyManager.Response;

@Controller
public class GroupFormationController {

	private static final String ID = "id";
	private static final String GROUPSIZE = "groupSize";
	FetchAlgorithmInput iAlgorithmPersistence = new FetchAlgorithmInput();
	private IUserPersistence userDB = SystemConfig.instance().getUserDB();

	@PostMapping("/groupformation/creategroups")
	public String formGroups(Model model, QuestionsList questions, @RequestParam(name = ID) long courseID,
			@RequestParam(name = GROUPSIZE) long groupSize) throws Exception {
		System.out.println("Questions" + questions);
		System.out.println("CourseID" + courseID);
		System.out.println("groupSize" + groupSize);
		System.out.println("group FormationAlgoStarts");
		
		String surveyID = iAlgorithmPersistence.findSurveyIDeWithCourseID(courseID);
		ArrayList<Response> responses = iAlgorithmPersistence.GetResponseByCourseID(courseID);
		List<Group> groups = new ArrayList<Group>();
		
		ArrayList<Long> userlist= new ArrayList<Long>();
		userlist=FormGroupsRandom.findUniqueUser(responses);
		
		long noOfGroups = (long) (Math.abs(userlist.size()/groupSize) + Math.ceil(userlist.size()%groupSize));
		int curentUser = 0;
		for(int i=0; i<noOfGroups;i++) {
			Group currentGroup = new Group();
			currentGroup.setGroupID(i+1);
			for(int j=0; j<groupSize && curentUser<userlist.size(); j++, curentUser++) {
				IUser user = UserAbstractFactory.getFactory().makeUser();
				userDB.loadUserByID(userlist.get(curentUser), user);
				currentGroup.addStudent((User)user);
			}
			groups.add(currentGroup);
		}
		
		model.addAttribute("groups", groups);
		return "survey/groupdisplay";
	}
}
