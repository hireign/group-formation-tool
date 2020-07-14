package CSCI5308.GroupFormationTool.SurveyManager;

import java.sql.Timestamp;
import java.util.List;

import CSCI5308.GroupFormationTool.QuestionManager.Question;

public class Survey {
	private long id;
	private long userID;
	private int active;
	private Timestamp createdAt;
	private List<Question> questions;
	private int index = 0;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public List<Question> getQuestions()
	{
		return questions;
	}

	public void setQuestions(List<Question> questions)
	{
		this.questions = questions;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void load(ISurveyPersistence surveyDB, long courseID) {
		Survey survey = surveyDB.loadSurveyByCourseID(courseID);
		
		if(survey.active == 1) {
			id = survey.id;
			userID = survey.userID;
			active = survey.active;
			questions = survey.questions;
			createdAt = survey.createdAt;
		}
	}

	public Question getNextQuestion() {
		Question question = null;
		
		if (index > -1 && index < questions.size()) {
			question = questions.get(index++);
		}
		
		return question;
	}
}
