package CSCI5308.GroupFormationTool.SurveyManager;

import java.sql.Timestamp;
import java.util.List;

import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;

public interface ISurvey {

	public long getId();

	public void setId(long id);

	public long getUserID();

	public void setUserID(long userID);

	public int getActive();

	public void setActive(int active);

	public Timestamp getCreatedAt();

	public void setCreatedAt(Timestamp createdAt);

	public List<IQuestion> getQuestions();

	public void setQuestions(List<IQuestion> questions);

}