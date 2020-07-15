package CSCI5308.GroupFormationTool.QuestionManager;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

@SuppressWarnings("deprecation")
public class QuestionTest
{
	@Test
	public void ConstructorTests()
	{
		IQuestion q = new Question();
		Assert.isTrue(q.getTitle().isEmpty());
		Assert.isTrue(q.getText().isEmpty());
		Assert.isNull(q.getType());
		Assert.isNull(q.getTimestamp());
	}
	
	@Test
	public void getTimestamp() 
	{
		IQuestion q = new Question();
		Timestamp time = Timestamp.valueOf("2020-06-16 00:00:00");
		q.setTimestamp(time);
		Assert.isTrue(time == q.getTimestamp());
	}
	
	@Test
	public void setTimestamp() 
	{
		IQuestion q = new Question();
		Timestamp time = Timestamp.valueOf("2020-06-16 00:00:00");
		q.setTimestamp(time);
		Assert.isTrue(time == q.getTimestamp());
	}
	
	@Test
	public void getId() 
	{
		IQuestion q = new Question();
		q.setId(7);
		Assert.isTrue(q.getId() == 7);
	}
	
	@Test
	public void setId() 
	{
		IQuestion q = new Question();
		q.setId(7);
		Assert.isTrue(q.getId() == 7);
	}
	
	@Test
	public void getTitle() 
	{
		IQuestion q = new Question();
		q.setTitle("Test title");
		Assert.isTrue(q.getTitle().equals("Test title"));
	}
	
	@Test
	public void setTitle() 
	{
		IQuestion q = new Question();
		q.setTitle("Test title");
		Assert.isTrue(q.getTitle().equals("Test title"));
	}
	
	@Test
	public void getText() 
	{
		IQuestion q = new Question();
		q.setText("Test text");
		Assert.isTrue(q.getText().equals("Test text"));
	}
	
	@Test
	public void setText() 
	{
		IQuestion q = new Question();
		q.setText("Test text");
		Assert.isTrue(q.getText().equals("Test text"));
	}
	
	@Test
	public void getType() 
	{
		IQuestion q = new Question();
		q.setType(QuestionType.TEXT);
		Assert.isTrue(q.getType() == QuestionType.TEXT);
	}
	
	@Test
	public void setType() 
	{
		IQuestion q = new Question();
		q.setType(QuestionType.TEXT);
		Assert.isTrue(q.getType() == QuestionType.TEXT);
	}
	
	@Test
	public void deleteQuestion() throws Exception 
	{
		IQuestion q = new Question();
		IQuestionPersistence questionDB = new QuestionDBMock();
		q.setDefaults();
		boolean status = questionDB.deleteQuestionByQuestionId(q.getId());
		Assert.isTrue(status == false);
		
		q.setId(1);
		q.setTitle("Test title");
		q.setText("Test text");
		q.setType(QuestionType.TEXT);
		q.setTimestamp(Timestamp.valueOf("2020-06-16 00:00:00"));
		status = questionDB.deleteQuestionByQuestionId(q.getId());
		Assert.isTrue(status == true);
	}

	@Test
	public void addSurveyQuestion() throws Exception
	{
		IQuestionPersistence questionDB = new QuestionDBMock();
		boolean status = questionDB.addSurveyQuestion(1,1,1);
		Assert.isTrue(status == true);
	}
}
