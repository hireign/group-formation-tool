package CSCI5308.GroupFormationTool.SurveyManager;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.Courses.ICourseUserRelationshipPersistence;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;

public interface SurveyIterator {

	public int getIndex();

	public void setIndex(int index);

	public void load(ISurveyPersistence surveyDB, long courseID,
					 ICourseUserRelationshipPersistence courseUserRelationshipDB, IUser user) throws Exception;

	public boolean hasNext();

	public IQuestion next();

	public int getQuestionSize();

	public long getId();

	public int getActive();

	public List<IQuestion> getQuestions();
}
