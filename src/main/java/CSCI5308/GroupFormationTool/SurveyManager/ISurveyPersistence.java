package CSCI5308.GroupFormationTool.SurveyManager;

import CSCI5308.GroupFormationTool.QuestionManager.Options;

public interface ISurveyPersistence {
	public Survey loadSurveyByCourseID(long courseID);
	
	public Options loadOptionsByQuestionID(long questionID);

	public boolean saveSurveyResponse(Response surveyResponse);
}
