package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;

public class Group {
	List<User> students = new ArrayList<User>();
	int groupID;
	
	public void addStudent(User student) {
		this.students.add(student);
	}

	public List<User> getStudents() {
		return students;
	}

	public void setStudents(List<User> students) {
		this.students = students;
	}

	public int getGroupID() {
		return groupID;
	}

	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
	
	
}
