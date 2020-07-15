package CSCI5308.GroupFormationTool.SurveyManagerTest;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.QuestionManager.Options;
import CSCI5308.GroupFormationTool.QuestionManager.Question;
import CSCI5308.GroupFormationTool.SurveyManager.IResponse;
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

	public void saveSurveyResponse(IResponse response) {
		if (response.getId() > -1 && response.getQuestionID() > -1
				&& response.getResponse() != "" && response.getUserID() > -1) {
			IResponse responseTest = new Response();
			responseTest.setId(response.getId());
		}
	}

	public boolean deleteSurveyQuestion(long questionID, long courseID) {
		Question question = new Question();
		question.setId(questionID);
		Survey survey = new Survey();
		survey.setId(courseID);
		if (question.getId() > -1 && survey.getId() > -1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean addSurveyQuestion(long questionID, long courseID, long instructorID){
		Survey survey = new Survey();
		survey.setId(courseID);
		survey.setUserID(instructorID);
		List<Question> questionList = new ArrayList<Question>();
		Question question = new Question();
		question.setId(questionID);
		questionList.add(question);
		survey.setQuestions(questionList);
		if(survey.getId() > -1 && survey.getUserID() > -1 && survey.getQuestions() != null){
			return true;
		}
		return false;
	}
}
