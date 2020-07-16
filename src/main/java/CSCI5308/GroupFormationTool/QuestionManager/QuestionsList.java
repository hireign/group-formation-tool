package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.ArrayList;
import java.util.List;

//Thymeleaf does not work with List of Objects it needs a special class which has list of objects. Hence Added this non sense.
public class QuestionsList {
	public List<Question> questionsList = new ArrayList<>();
	
	public void addQuestion(Question question) {
		this.questionsList.add(question);
	}

	public List<Question> getQuestionsList() {
		return questionsList;
	}

	public void setQuestionsList(List<Question> questionsList) {
		this.questionsList = questionsList;
	}
	
}
