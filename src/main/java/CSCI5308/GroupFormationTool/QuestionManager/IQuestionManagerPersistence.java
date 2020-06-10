package CSCI5308.GroupFormationTool.QuestionManager;

import CSCI5308.GroupFormationTool.Courses.Course;

import java.util.List;

public interface IQuestionManagerPersistence {
    public List<Question> loadAllQuestionsByInstructor(int instructorId);
    public boolean createQuestion(Question question);
    public boolean deleteQuestion(int id);
}
