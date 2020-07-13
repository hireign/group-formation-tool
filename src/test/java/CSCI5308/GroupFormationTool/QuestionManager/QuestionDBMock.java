package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.ArrayList;
import java.util.List;

public class QuestionDBMock implements IQuestionPersistence 
{
	List<Question> questions;
	
	public List<Question> loadQuestionsSortedByTitle(String bannerId) 
	{
		questions = new ArrayList<Question>();
		if(bannerId.equals("B-000000")) 
		{
			Question q = new Question();
			q.setId(1);
			q.setTitle("Test Title");
			q.setText("Test Question");
			q.setType(QuestionType.TEXT);
			questions.add(q);
			
			q = new Question();
			q.setId(1);
			q.setTitle("Test Title 2");
			q.setText("Test Question");
			q.setType(QuestionType.TEXT);
			questions.add(q);
		}
		return questions;
	}

	public List<Question> loadSortedQuestionsSortedByDate(String bannerId) 
	{
		questions = new ArrayList<Question>();
		if(bannerId.equals("B-000000")) 
		{
			Question q = new Question();
			q.setId(1);
			q.setTitle("Test Title 2");
			q.setText("Test Question");
			q.setType(QuestionType.TEXT);
			questions.add(q);
			
			q = new Question();
			q.setId(1);
			q.setTitle("Test Title");
			q.setText("Test Question");
			q.setType(QuestionType.TEXT);
			questions.add(q);
		}
		return questions;
	}

	public boolean deleteQuestionByQuestionId(long questionId) 
	{
		Question q = new Question();
		q.setId(1);
		q.setTitle("Test Title");
		q.setText("Test Question");
		q.setType(QuestionType.TEXT);
		
		if(questionId>-1) 
		{
			q.setDefaults();
			return true;
		}
		return false;
	}

	@Override
	public long createQuestion(Question question, String bannerID) 
	{
		if(question.getId()>-1) 
		{
			return 1;
		}
		return 0;
	}

	@Override
	public boolean createQuestionOption(OptionValue option, int order, long questionID) 
	{
		if(questionID == -1 || isStringEmpty(option.getText()) || isStringEmpty(option.getStoredAs())) 
		{
			return false;
		}
		return true;
	}
	
	public boolean isStringEmpty(String s) 
	{
		return s.replaceAll(" ","").length() == 0;
	}

}
