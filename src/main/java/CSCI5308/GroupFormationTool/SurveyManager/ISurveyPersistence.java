package CSCI5308.GroupFormationTool.SurveyManager;

import CSCI5308.GroupFormationTool.QuestionManager.IOptions;

public interface ISurveyPersistence {

	public ISurvey loadSurveyByCourseID(long courseID) throws Exception;

	public IOptions loadOptionsByQuestionID(long questionID) throws Exception;

	public void saveSurveyResponse(IResponse surveyResponse) throws Exception;

	public boolean deleteSurveyQuestion(long questionID, long courseID) throws Exception;

	public boolean addSurveyQuestion(long questionID, long courseID, long instructorID) throws Exception;
}
