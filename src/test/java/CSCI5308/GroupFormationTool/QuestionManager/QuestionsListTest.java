package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.ArrayList;
import java.util.List;
import org.springframework.util.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuestionsListTest {
	
	private QuestionAbstractFactory questionFactory = QuestionAbstractFactory.getFactory();
	
    @SuppressWarnings("deprecation")
	@Test
    public void setQuestionListTest() {
        List<Question> questionsList = new ArrayList<>();
        QuestionsList questions = new QuestionsList();
        IQuestion question = questionFactory.makeQuestion();
        question.setText("What is mobile number? Karu kya dial number?");
        questionsList.add((Question)question);
        questions.setQuestionsList(questionsList);
        Assert.isTrue(questions.getQuestionsList().get(0).getText().equals("What is mobile number? Karu kya dial number?"));
    }
    
    @SuppressWarnings("deprecation")
	@Test
    public void getQuestionsListTest() {
    	List<Question> questionsList = new ArrayList<>();
        QuestionsList questions = new QuestionsList();
        IQuestion question = questionFactory.makeQuestion();
        question.setText("Call me on my cell phone....");
        questionsList.add((Question)question);
        questions.setQuestionsList(questionsList);
        Assert.isTrue(questions.getQuestionsList().get(0).getText().equals("Call me on my cell phone...."));
    }
    
    @SuppressWarnings("deprecation")
	@Test
    public void addQuestionTest() {
    	List<Question> questionsList = new ArrayList<>();
        QuestionsList questions = new QuestionsList();
        IQuestion question = questionFactory.makeQuestion();
        question.setText("Hotline Bling!");
        questionsList.add((Question)question);
        question = questionFactory.makeQuestion();
        question.setTitle("Govinda/Drake");
        questionsList.add((Question)question);
        questions.setQuestionsList(questionsList);
        Assert.isTrue(questions.getQuestionsList().get(0).getText() == "Hotline Bling!");
        Assert.isTrue(questions.getQuestionsList().get(1).getTitle() == "Govinda/Drake");
    }

}
