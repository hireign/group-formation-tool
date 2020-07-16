package CSCI5308.GroupFormationTool.GroupFormation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionsList;

@Controller
public class GroupFormationController {

	private static final String ID = "id";
	private static final String GROUPSIZE = "groupSize";

	@GetMapping("/groupformation/creategroups")
	public String sendToGroups(Model model) {
		System.out.println("groupFormationAlgoStarts");
		return "index";
	}

	@PostMapping("/groupformation/creategroups")
	public String formGroups(Model model, QuestionsList questions, @RequestParam(name = ID) long courseID,
			@RequestParam(name = GROUPSIZE) long groupSize) {
		System.out.println("Questions" + questions);
		System.out.println("CourseID" + courseID);
		System.out.println("groupSize" + groupSize);
		System.out.println("group FormationAlgoStarts");
		return "index";
	}
}
