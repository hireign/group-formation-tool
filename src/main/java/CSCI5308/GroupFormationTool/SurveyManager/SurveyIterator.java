package CSCI5308.GroupFormationTool.SurveyManager;

import java.util.List;

import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;

public interface SurveyIterator {

	public int getIndex();

	public void setIndex(int index);
	
	public void load(ISurveyPersistence surveyDB, long courseID) throws Exception;
	
	public boolean hasNext();

	public IQuestion next();

	public int getQuestionSize();

	public long getId();

	public int getActive();

	public List<IQuestion> getQuestions();
}
