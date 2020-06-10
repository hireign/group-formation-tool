package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.List;

public class QuestionManagerDB implements IQuestionManagerPersistence {
    @Override
    public List<Question> loadAllQuestionsByInstructor(int instructorId) {
        return null;
    }

    @Override
    public boolean createQuestion(Question question) {
        return false;
    }

    @Override
    public boolean deleteQuestion(int id) {
        return false;
    }
}
