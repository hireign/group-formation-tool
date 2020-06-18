package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.List;

public interface IQuestionPersistence {
	public List<Question> loadAllQuestionsByInstructor(long l);

	public boolean create(Question question);

	public boolean delete(long id);

}
