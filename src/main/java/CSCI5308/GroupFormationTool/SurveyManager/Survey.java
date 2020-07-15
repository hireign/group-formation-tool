package CSCI5308.GroupFormationTool.SurveyManager;

import java.sql.Timestamp;
import java.util.List;

import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;

public class Survey implements ISurvey {
	private long id;
	private long userID;
	private int active;
	private Timestamp createdAt;
	private List<IQuestion> questions;
	private int index = 0;

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
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
	public int getActive() {
		return active;
	}

	@Override
	public void setActive(int active) {
		this.active = active;
	}

	@Override
	public Timestamp getCreatedAt() {
		return createdAt;
	}

	@Override
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public List<IQuestion> getQuestions() {
		return questions;
	}

	@Override
	public void setQuestions(List<IQuestion> questions) {
		this.questions = questions;
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public int getQuestionSize() {
		return questions.size();
	}

	@Override
	public void load(ISurveyPersistence surveyDB, long courseID) throws Exception {
		ISurvey survey = surveyDB.loadSurveyByCourseID(courseID);

		if (survey != null && survey.getActive() == 1) {
			id = survey.getId();
			userID = survey.getUserID();
			active = survey.getActive();
			questions = survey.getQuestions();
			createdAt = survey.getCreatedAt();
		}
	}

	@Override
	public IQuestion getNextQuestion() {
		IQuestion question = null;

		if (index > -1 && index < questions.size()) {
			question = questions.get(index++);
		}

		return question;
	}
}
