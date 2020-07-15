package CSCI5308.GroupFormationTool.SurveyManager;

import java.util.List;

public interface IResponse {

	long getId();

	void setId(long id);

	long getQuestionID();

	void setQuestionID(long questionID);

	long getUserID();

	void setUserID(long userID);

	String getResponse();

	void setResponse(String response);

	long getSurveyID();

	void setSurveyID(long surveyID);

	List<String> getOptions();

	void setOptions(List<String> options);

	void convertOptionListToResponseString();

	boolean save(ISurveyPersistence surveyDB);

}