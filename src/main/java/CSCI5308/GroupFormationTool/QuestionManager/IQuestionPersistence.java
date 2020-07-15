package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.List;

public interface IQuestionPersistence 
{
	public List<IQuestion> loadQuestionsSortedByTitle(String bannerId) throws Exception;
	
	public List<IQuestion> loadSortedQuestionsSortedByDate(String bannerId) throws Exception;
	
	public boolean deleteQuestionByQuestionId(long questionId) throws Exception;

	public long createQuestion(IQuestion question, String bannerID) throws Exception;
	
	public boolean createQuestionOption(IOptionValue option, int order, long questionID) throws Exception;
	public boolean addSurveyQuestion(long questionID, long courseID, long instructorID) throws Exception;	
}
