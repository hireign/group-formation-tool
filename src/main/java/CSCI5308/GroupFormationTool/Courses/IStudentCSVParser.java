package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.IUser;

public interface IStudentCSVParser 
{

	public List<IUser> parseCSVFile(List<String> failureResults);
	
}
