package CSCI5308.GroupFormationTool.SurveyManagerTest;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.QuestionManager.Options;
import CSCI5308.GroupFormationTool.QuestionManager.Question;
import CSCI5308.GroupFormationTool.SurveyManager.ISurveyPersistence;
import CSCI5308.GroupFormationTool.SurveyManager.Response;
import CSCI5308.GroupFormationTool.SurveyManager.Survey;

public class SurveyDBMock implements ISurveyPersistence {
	public Survey loadSurveyByCourseID(long courseID) {
		Survey survey = new Survey();
		List<Question> questions = new ArrayList<Question>();
		Question question = new Question();

		question.setId(1);
		question.setTitle("Programming Expertise");
		question.setText("Rate your programming skills");
		question.setOptions(loadOptionsByQuestionID(1));
		questions.add(question);

		survey.setId(1);
		survey.setUserID(1);
		survey.setQuestions(questions);
		survey.setActive(1);
		return survey;
	}

	public Options loadOptionsByQuestionID(long questionID) {
		Options options = new Options();
		options.setDefault();
		return options;
	}

	public boolean saveSurveyResponse(Response response) {
		if (response.getId() > -1 && response.getQuestionID() > -1
				&& response.getResponse() != "" && response.getUserID() > -1) {
			return true;
		}
		return false;
	}
}
