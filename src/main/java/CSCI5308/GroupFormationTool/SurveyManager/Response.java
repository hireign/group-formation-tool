package CSCI5308.GroupFormationTool.SurveyManager;

import java.util.List;

public class Response implements IResponse {
	private long id;
	private long questionID;
	private long userID;
	private String response;
	private long surveyID;
	private List<String> options;

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public long getQuestionID() {
		return questionID;
	}

	@Override
	public void setQuestionID(long questionID) {
		this.questionID = questionID;
	}

	@Override
	public long getUserID() {
		return userID;
	}

	@Override
	public void setUserID(long userID) {
		this.userID = userID;
	}

	@Override
	public String getResponse() {
		return response;
	}

	@Override
	public void setResponse(String response) {
		this.response = response;
	}

	@Override
	public long getSurveyID() {
		return surveyID;
	}

	@Override
	public void setSurveyID(long surveyID) {
		this.surveyID = surveyID;
	}

	@Override
	public List<String> getOptions() {
		return options;
	}

	@Override
	public void setOptions(List<String> options) {
		this.options = options;
	}

	@Override
	public void convertOptionListToResponseString() {
		if (options == null) {
			return;
		}
		if(response == null) {
			response = "";
		}
		for (String option : this.options) {
			if (option == "") {
				continue;
			}
			
			response += option;
			response += ",";
		}
	}
	
	@Override
	public boolean save(ISurveyPersistence surveyDB) {
		convertOptionListToResponseString();
		return surveyDB.saveSurveyResponse(this);
	}

}
