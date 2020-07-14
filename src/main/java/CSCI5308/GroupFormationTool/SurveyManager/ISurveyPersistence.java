package CSCI5308.GroupFormationTool.SurveyManager;

import CSCI5308.GroupFormationTool.QuestionManager.IOptions;

public interface ISurveyPersistence {
	
	public ISurvey loadSurveyByCourseID(long courseID);
	public IOptions loadOptionsByQuestionID(long questionID);
	public boolean saveSurveyResponse(IResponse surveyResponse);
}
