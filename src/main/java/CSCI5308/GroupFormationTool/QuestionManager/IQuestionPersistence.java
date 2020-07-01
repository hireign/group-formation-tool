package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.List;

public interface IQuestionPersistence 
{
	public List<Question> loadQuestionsSortedByTitle(String bannerId);
	
	public List<Question> loadSortedQuestionsSortedByDate(String bannerId);
	
	public boolean deleteQuestionByQuestionId(long questionId);

	public long createQuestion(Question question, String bannerID);
	
	public boolean createQuestionOption(OptionValue option, int order, long questionID);
	
}
