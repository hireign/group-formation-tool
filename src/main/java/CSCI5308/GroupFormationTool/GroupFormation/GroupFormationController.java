package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
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
		
		Long[] testuserlist = new Long[userlist.size()];
        for(int i=0;i<userlist.size();i++) {
            testuserlist[i] = userlist.get(i);
        }
        
        HashMap<Long, HashMap<Long,String>> userHashmap = FormGroupsCluster.getUserResponseByQuestion(userlist, responses);
        
        int[][] twoD_array = FormGroupsCluster.simiarityTwoDArray(userHashmap,testuserlist);
        Integer[][] formedGroups = FormGroupsCluster.groupFormation(groupSize, twoD_array);
        groups = FormGroupsCluster.convertToUserList(testuserlist, formedGroups);
		
		model.addAttribute("groups", groups);
		return "survey/groupdisplay";
	}
}
