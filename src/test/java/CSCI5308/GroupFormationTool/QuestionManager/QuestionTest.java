package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
@SuppressWarnings("deprecation")
public class QuestionTest {
	private static final int instructorId = 1;
	private static final int questionId = 1;
	@Test
	public void getIdTest() {
		Question question = new Question();
		question.setId(7);
		Assert.isTrue(question.getId() == 7);
	}

	@Test
	public void setIdTest() {
		Question question = new Question();
		question.setId(7);
		Assert.isTrue(question.getId() == 7);
	}

	@Test
	public void getTitleTest() {
		Question question = new Question();
		question.setTitle("CSCI 5308");
		Assert.isTrue(question.getTitle().equals("CSCI 5308"));
	}

	@Test
	public void setTitleTest() {
		Question question = new Question();
		question.setTitle("CSCI 5308");
		Assert.isTrue(question.getTitle().equals("CSCI 5308"));
	}

	@Test
	public void getTextTest() {
		Question question = new Question();
		question.setText("CSCI 5308");
		Assert.isTrue(question.getText().equals("CSCI 5308"));
	}

	@Test
	public void setTextTest() {
		Question question = new Question();
		question.setText("CSCI 5308");
		Assert.isTrue(question.getText().equals("CSCI 5308"));
	}

	@Test
	public void getTypeTest() {
		Question question = new Question();
		question.setType(QuestionType.NUMBER.toString());
		Assert.isTrue(question.getType() == QuestionType.NUMBER.toString());
	}

	@Test
	public void setTypeTest() {
		Question question = new Question();
		question.setType(QuestionType.NUMBER.toString());
		Assert.isTrue(question.getType() == QuestionType.NUMBER.toString());
	}

	@Test
	public void getInstructorIdTest() {
		Question question = new Question();
		question.setInstructorId(7);
		Assert.isTrue(question.getInstructorId() == 7);
	}

	@Test
	public void setInstructorIdTest() {
		Question question = new Question();
		question.setInstructorId(7);
		Assert.isTrue(question.getInstructorId() == 7);
	}

	@Test
	public void loadAllQuestionsByInstructor() {
		QuestionDBMock questionDBMock = new QuestionDBMock();
		List<Question> questionList = new ArrayList<>();
		questionList = questionDBMock.loadAllQuestionsByInstructor(instructorId);
		Assert.isTrue(questionList.size()==2);
	}

	@Test
	public void createQuestionTest(){
		QuestionDBMock questionDBMock = new QuestionDBMock();
		Question question = new Question();
		Boolean result = questionDBMock.create(question);
		Assert.isTrue(question.getTitle()=="Code Quality");
		Assert.isTrue(result);
	}

	@Test
	public void deleteQuestionTest(){
		QuestionDBMock questionDBMock = new QuestionDBMock();
		Boolean result = questionDBMock.delete(questionId);
		Assert.isTrue(result);
	}
}
