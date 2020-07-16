package CSCI5308.GroupFormationTool.SurveyManagerTest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.TestSystemConfig;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionAbstractFactory;
import CSCI5308.GroupFormationTool.SurveyManager.ISurvey;
import CSCI5308.GroupFormationTool.SurveyManager.ISurveyPersistence;
import CSCI5308.GroupFormationTool.SurveyManager.SurveyAbstractFactory;

public class SurveyTest {

	private SurveyAbstractFactory surveyFactory = SurveyAbstractFactory.getFactory();
	private QuestionAbstractFactory questionFactory = QuestionAbstractFactory.getFactory();

	@Test
	public void getIdTest() {
		ISurvey survey = surveyFactory.createSurvey();
		survey.setId(1);
		Assert.isTrue(1 == survey.getId());
	}

	@Test
	public void setIdTest() {
		ISurvey survey = surveyFactory.createSurvey();
		survey.setId(1);
		Assert.isTrue(1 == survey.getId());
	}

	@Test
	public void getUserIDTest() {
		ISurvey survey = surveyFactory.createSurvey();
		survey.setUserID(1);
		Assert.isTrue(1 == survey.getUserID());
	}

	@Test
	public void setUserIDTest() {
		ISurvey survey = surveyFactory.createSurvey();
		survey.setUserID(1);
		Assert.isTrue(1 == survey.getUserID());
	}

	@Test
	public void getActiveTest() {
		ISurvey survey = surveyFactory.createSurvey();
		survey.setActive(1);
		Assert.isTrue(1 == survey.getActive());
	}

	@Test
	public void setActiveTest() {
		ISurvey survey = surveyFactory.createSurvey();
		survey.setActive(1);
		Assert.isTrue(1 == survey.getActive());
	}

	@Test
	public void getCreatedAtTest() {
		ISurvey survey = surveyFactory.createSurvey();
		Timestamp time = Timestamp.valueOf("2020-07-10 00:00:00");
		survey.setCreatedAt(time);
		Assert.isTrue(time == survey.getCreatedAt());
	}

	@Test
	public void setCreatedAtTest() {
		ISurvey survey = surveyFactory.createSurvey();
		Timestamp time = Timestamp.valueOf("2020-07-10 00:00:00");
		survey.setCreatedAt(time);
		Assert.isTrue(time == survey.getCreatedAt());
	}

	@Test
	public void getQuestionsTest() {
		ISurvey survey = surveyFactory.createSurvey();
		List<IQuestion> questions = new ArrayList<IQuestion>();
		IQuestion question = questionFactory.createQuestion();
		question.setId(1);
		questions.add(question);
		survey.setQuestions(questions);
		Assert.isTrue(1 == survey.getQuestions().get(0).getId());
	}

	@Test
	public void setQuestionsTest() {
		ISurvey survey = surveyFactory.createSurvey();
		List<IQuestion> questions = new ArrayList<IQuestion>();
		IQuestion question = questionFactory.createQuestion();
		question.setId(1);
		questions.add(question);
		survey.setQuestions(questions);
		Assert.isTrue(1 == survey.getQuestions().get(0).getId());
	}

	@Test
	public void getAllQuestions() {
		List<IQuestion> questions = new ArrayList<IQuestion>();
		IQuestion question = questionFactory.createQuestion();
		question.setId(1);
		questions.add(question);
		Assert.isTrue(1 == questions.get(0).getId());
	}

	@Test
	public void getNextQuestion() {
		IQuestion question = questionFactory.createQuestion();
		question.setId(1);
		Assert.isTrue(1 == question.getId());
	}

	@Test
	public void deleteSurveyQuestion() throws Exception {
		ISurveyPersistence surveyDBMock = TestSystemConfig.instance().getSurveyDB();
		boolean status = surveyDBMock.deleteSurveyQuestion(1, 1);
		Assert.isTrue(status == true);
	}

	@Test
	public void addSurveyQuestion() throws Exception {
		ISurveyPersistence surveyDBMock = TestSystemConfig.instance().getSurveyDB();
		boolean status = surveyDBMock.addSurveyQuestion(1, 1, 1);
		Assert.isTrue(status == true);
	}


	@Test
	public void publishSurvey() throws Exception {
		ISurveyPersistence surveyDBMock = TestSystemConfig.instance().getSurveyDB();
		boolean status = surveyDBMock.publishSurvey(1);
		Assert.isTrue(status == true);
	}
}
