package CSCI5308.GroupFormationTool.SurveyManagerTest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.QuestionManager.Question;
import CSCI5308.GroupFormationTool.SurveyManager.Survey;

public class SurveyTest {

	@Test
	public void getIdTest() {
		Survey survey = new Survey();
		survey.setId(1);
		Assert.isTrue(1 == survey.getId());
	}

	@Test
	public void setIdTest() {
		Survey survey = new Survey();
		survey.setId(1);
		Assert.isTrue(1 == survey.getId());
	}

	@Test
	public void getUserIDTest() {
		Survey survey = new Survey();
		survey.setUserID(1);
		Assert.isTrue(1 == survey.getUserID());
	}

	@Test
	public void setUserIDTest() {
		Survey survey = new Survey();
		survey.setUserID(1);
		Assert.isTrue(1 == survey.getUserID());
	}

	@Test
	public void getActiveTest() {
		Survey survey = new Survey();
		survey.setActive(1);
		Assert.isTrue(1 == survey.getActive());
	}

	@Test
	public void setActiveTest() {
		Survey survey = new Survey();
		survey.setActive(1);
		Assert.isTrue(1 == survey.getActive());
	}

	@Test
	public void getCreatedAtTest() {
		Survey survey = new Survey();
		Timestamp time = Timestamp.valueOf("2020-07-10 00:00:00");
		survey.setCreatedAt(time);
		Assert.isTrue(time == survey.getCreatedAt());
	}

	@Test
	public void setCreatedAtTest() {
		Survey survey = new Survey();
		Timestamp time = Timestamp.valueOf("2020-07-10 00:00:00");
		survey.setCreatedAt(time);
		Assert.isTrue(time == survey.getCreatedAt());
	}

	@Test
	public void getQuestionsTest() {
		Survey survey = new Survey();
		List<Question> questions = new ArrayList<Question>();
		Question question = new Question();
		question.setId(1);
		questions.add(question);
		survey.setQuestions(questions);
		Assert.isTrue(1 == survey.getQuestions().get(0).getId());
	}

	@Test
	public void setQuestionsTest() {
		Survey survey = new Survey();
		List<Question> questions = new ArrayList<Question>();
		Question question = new Question();
		question.setId(1);
		questions.add(question);
		survey.setQuestions(questions);
		Assert.isTrue(1 == survey.getQuestions().get(0).getId());
	}
	
	@Test
	public void getAllQuestions() {
		List<Question> questions = new ArrayList<Question>();
		Question question = new Question();
		question.setId(1);
		questions.add(question);
		Assert.isTrue(1 == questions.get(0).getId());
	}
	
	@Test
	public void getNextQuestion() {
		Question question = new Question();
		question.setId(1);
		Assert.isTrue(1 == question.getId());
	}
}
