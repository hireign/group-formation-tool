package CSCI5308.GroupFormationTool.SurveyManager;

import java.sql.Timestamp;
import java.util.List;

import CSCI5308.GroupFormationTool.QuestionManager.Question;

public interface ISurvey {

	long getId();

	void setId(long id);

	long getUserID();

	void setUserID(long userID);

	int getActive();

	void setActive(int active);

	Timestamp getCreatedAt();

	void setCreatedAt(Timestamp createdAt);

	List<Question> getQuestions();

	void setQuestions(List<Question> questions);

	int getIndex();

	void setIndex(int index);

	int getQuestionSize();

	void load(ISurveyPersistence surveyDB, long courseID);

	Question getNextQuestion();

}