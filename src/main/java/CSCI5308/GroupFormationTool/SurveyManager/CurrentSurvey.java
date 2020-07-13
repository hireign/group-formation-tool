package CSCI5308.GroupFormationTool.SurveyManager;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.QuestionManager.Question;

public class CurrentSurvey {
	private static Survey survey = null;
	private static String courseID = "";
	private ISurveyPersistence surveyDB = null;
	private static CurrentSurvey uniqueInstance = null;
	List<Question> questions = null;
	private int index;

	private CurrentSurvey() {
		index = 0;
		surveyDB = SystemConfig.instance().getSurveyDB();
		questions = new ArrayList<Question>();
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public static CurrentSurvey instance() {
		if (null == uniqueInstance) {
			uniqueInstance = new CurrentSurvey();
		}
		return uniqueInstance;
	}

	public static String getCourseID() {
		return courseID;
	}

	public static void setCourseID(String courseID) {
		CurrentSurvey.courseID = courseID;
	}

	public Survey getSurvey() {
		return survey;
	}
	
	public void setSurvey() {
		survey = surveyDB.loadSurveyByCourseID(courseID);
		getAllQuestions();
	}

	public void getAllQuestions() {
		if (survey != null) {
			questions = survey.getQuestions();
		}
	}

	public Question getNextQuestion() {
		Question question = null;
		if (index > -1) {
			question = questions.get(index++);
		}
		return question;
	}
}
