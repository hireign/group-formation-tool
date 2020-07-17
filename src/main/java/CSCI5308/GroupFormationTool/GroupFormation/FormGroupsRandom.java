package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.SurveyManager.Response;

public class FormGroupsRandom {

	public static ArrayList<Long> findUniqueUser(ArrayList<Response> responses){
	ArrayList<Long> uniqueUserList = new ArrayList<Long>();
	for (int i=0; i<responses.size(); i++){
	    if (!uniqueUserList.contains(responses.get(i).getUserID())){
	        uniqueUserList.add(responses.get(i).getUserID());
	    }
	}
	return uniqueUserList;
	}
}
