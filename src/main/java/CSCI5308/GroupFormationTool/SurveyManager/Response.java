package CSCI5308.GroupFormationTool.SurveyManager;

import java.util.List;

public class Response {
	private long id;
	private long questionID;
	private long userID;
	private String response;
	private long surveyID;
	private List<String> options;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getQuestionID() {
		return questionID;
	}

	public void setQuestionID(long questionID) {
		this.questionID = questionID;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public long getSurveyID() {
		return surveyID;
	}

	public void setSurveyID(long surveyID) {
		this.surveyID = surveyID;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

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
	
	public boolean save(ISurveyPersistence surveyDB) {
		convertOptionListToResponseString();
		return surveyDB.saveSurveyResponse(this);
	}

}
