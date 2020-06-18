package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.List;

public interface IQuestionPersistence {
	public List<Question> loadAllQuestionsByInstructor(long l);

	public boolean create(Question question);

	public boolean delete(long id);

	public List<Question> sortByTitle(List<Question> questions);
	
	public List<Question> sortByDate(List<Question> questions);

}
