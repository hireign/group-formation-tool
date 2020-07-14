package CSCI5308.GroupFormationTool.SurveyManager;

import java.sql.Timestamp;
import java.util.List;

import CSCI5308.GroupFormationTool.QuestionManager.Question;

public class Survey implements ISurvey {
	private long id;
	private long userID;
	private int active;
	private Timestamp createdAt;
	private List<Question> questions;
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
	public List<Question> getQuestions()
	{
		return questions;
	}

	@Override
	public void setQuestions(List<Question> questions)
	{
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
	public void load(ISurveyPersistence surveyDB, long courseID) {
		Survey survey = (Survey)surveyDB.loadSurveyByCourseID(courseID);
		
		if(survey.active == 1) {
			id = survey.id;
			userID = survey.userID;
			active = survey.active;
			questions = survey.questions;
			createdAt = survey.createdAt;
		}
	}

	@Override
	public Question getNextQuestion() {
		Question question = null;
		
		if (index > -1 && index < questions.size()) {
			question = questions.get(index++);
		}
		
		return question;
	}
}
