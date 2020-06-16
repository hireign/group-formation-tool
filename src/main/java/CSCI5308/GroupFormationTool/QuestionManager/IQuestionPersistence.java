package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.List;

public interface IQuestionPersistence {
	public List<Question> loadAllQuestionsByInstructor(int instructorId);

	public boolean create(Question question);

	public boolean delete(long id);

}
