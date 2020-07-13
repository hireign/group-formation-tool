package CSCI5308.GroupFormationTool.SurveyManagerTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.QuestionManager.Question;

@SuppressWarnings("deprecation")
public class CurrentSurveyMock {
	
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
