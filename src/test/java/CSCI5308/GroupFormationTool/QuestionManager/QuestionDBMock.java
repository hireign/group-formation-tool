package CSCI5308.GroupFormationTool.QuestionManager;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class QuestionDBMock implements IQuestionPersistence  {
    public List<Question> loadAllQuestionsByInstructor(long instructorId) {
    	List<Question> questionList = new ArrayList<>();
		Question question = new Question();
		question.setId(0);
		question.setTitle("Code Quality");
		question.setText("Design patterns implemented");
		question.setType(QuestionType.NUMBER.toString());
		question.setInstructorId(1);
		questionList.add(question);
		question = new Question();
		question.setId(1);
		question.setTitle("Testing");
		question.setText("Test classes implemented");
		question.setType(QuestionType.CHECKBOX.toString());
		question.setInstructorId(1);
		questionList.add(question);
		return questionList;
    }

    public boolean create(Question question) {
		question.setId(0);
		question.setTitle("Code Quality");
		question.setText("Design patterns implemented");
		question.setType(QuestionType.NUMBER.toString());
		question.setInstructorId(1);
		return true;
    }

    public boolean delete(long id) {
    	Question question = new Question();
		question.setId(id);
		question.setTitle("Code Quality");
		question.setText("Design patterns implemented");
		question.setType(QuestionType.NUMBER.toString());
		question.setInstructorId(1);
		return true;
    }

	@Override
	public List<Question> sortByTitle(List<Question> questions) {
		QuestionDBMock questionDBMock = new QuestionDBMock();
		questions.get(0).setTitle("A");
		questions.get(1).setTitle("B");
		return questions;
	}

	@Override
	public List<Question> sortByDate(List<Question> questions) {
		QuestionDBMock questionDBMock = new QuestionDBMock();
		questions.get(0).setDate(LocalDateTime.of(2020, Month.JUNE, 18, 19, 30, 40));
		questions.get(1).setDate(LocalDateTime.of(2021, Month.JUNE, 18, 19, 30, 40));
		return questions;
	}
}
