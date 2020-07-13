package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.List;

public interface IQuestionPersistence 
{
	public List<Question> loadQuestionsSortedByTitle(String bannerId) throws Exception;
	
	public List<Question> loadSortedQuestionsSortedByDate(String bannerId) throws Exception;
	
	public boolean deleteQuestionByQuestionId(long questionId) throws Exception;

	public long createQuestion(Question question, String bannerID) throws Exception;
	
	public boolean createQuestionOption(OptionValue option, int order, long questionID) throws Exception;
	
}
