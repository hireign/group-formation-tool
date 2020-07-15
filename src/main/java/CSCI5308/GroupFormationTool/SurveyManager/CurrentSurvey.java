package CSCI5308.GroupFormationTool.SurveyManager;

import java.sql.Timestamp;
import java.util.List;

import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;

public class CurrentSurvey implements SurveyIterator {
	private ISurvey survey = SurveyAbstractFactory.getFactory().createSurvey();
	private int index;
	
	public CurrentSurvey() {
		index = 0;
	}
	
	public long getId() {
		return survey.getId();
	}

	public void setId(long id) {
		survey.setId(id);
	}

	public long getUserID() {
		return survey.getUserID();
	}

	public void setUserID(long userID) {
		survey.setUserID(userID);
	}

	public int getActive() {
		return survey.getActive();
	}

	public void setActive(int active) {
		survey.setActive(active);
	}

	public Timestamp getCreatedAt() {
		return survey.getCreatedAt();
	}

	public void setCreatedAt(Timestamp createdAt) {
		survey.setCreatedAt(createdAt);
	}

	public List<IQuestion> getQuestions() {
		return survey.getQuestions();
	}

	public void setQuestions(List<IQuestion> questions) {
		survey.setQuestions(questions);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void load(ISurveyPersistence surveyDB, long courseID) throws Exception {
		ISurvey survey = surveyDB.loadSurveyByCourseID(courseID);

		if (survey != null && survey.getActive() == 1) {
			this.survey.setId(survey.getId());
			this.survey.setUserID(survey.getUserID());
			this.survey.setActive(survey.getActive());
			this.survey.setQuestions(survey.getQuestions());
			this.survey.setCreatedAt(survey.getCreatedAt());
		}
		else if(survey == null) {
			this.survey.setId(-1);
		}
	}

	public boolean hasNext() {
		if(index < survey.getQuestions().size()) {
			return true;
		}
		return false;
	}

	public IQuestion next() {
		IQuestion question = null;

		if (index > -1 && index < survey.getQuestions().size()) {
			question = survey.getQuestions().get(index++);
		}

		return question;
	}
	
	public int getQuestionSize() {
		return survey.getQuestions().size();
	}
}
