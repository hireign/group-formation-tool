package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

public interface IStudentCSVImport {

	public void enrollStudentFromRecord();

	public List<String> getSuccessResults();

	public List<String> getFailureResults();
}
