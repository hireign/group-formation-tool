package CSCI5308.GroupFormationTool.QuestionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class QuestionDB implements IQuestionPersistence 
{
	@Override
	public List<Question> loadQuestionsSortedByTitle(String bannerID) 
	{
		List<Question> questionList = new ArrayList<Question>();
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spFindSortedQuestionsByTitle(?)");
			proc.setParameter(1, bannerID);
			ResultSet results = proc.executeWithResults();
			Question question;
			
			if (null != results)
			{
				while (results.next())
				{
					long id = results.getLong(1);
					String title = results.getString(2);
					String text = results.getString(3);
					QuestionType type = QuestionType.valueOf(results.getString(4).toUpperCase());
					Timestamp timestamp = results.getTimestamp(5);
					
					question = new Question();
					question.setId(id);
					question.setTitle(title);
					question.setText(text);
					question.setType(type);
					question.setTimestamp(timestamp);
					questionList.add(question);
				}
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
			// Logging needed.
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return questionList;
	}
	
	@Override
	public List<Question> loadSortedQuestionsSortedByDate(String bannerID) 
	{
		List<Question> questionList = new ArrayList<Question>();
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spFindSortedQuestionsByDate(?)");
			proc.setParameter(1, bannerID);
			ResultSet results = proc.executeWithResults();
			Question question;
			
			if (null != results)
			{
				while (results.next())
				{
					long id = results.getLong(1);
					String title = results.getString(2);
					String text = results.getString(3);
					QuestionType type = QuestionType.valueOf(results.getString(4).toUpperCase());
					Timestamp timestamp = results.getTimestamp(5);
					
					question = new Question();
					question.setId(id);
					question.setTitle(title);
					question.setText(text);
					question.setType(type);
					question.setTimestamp(timestamp);
					questionList.add(question);
				}
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
			// Logging needed.
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return questionList;
	}
	
	@Override
	public boolean deleteQuestionByQuestionId(long questionID) 
	{
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spDeleteQuestionsByQuestionID(?)");
			proc.setParameter(1, questionID);
			proc.execute();
		}
		catch (SQLException e)
		{
			System.out.println(e);
			return false;
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return true;
	}
	
	@Override
	public long createQuestion(Question question, String bannerID) 
	{
		long id=-1;
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spCreateQuestion(?,?,?,?)");
			proc.setParameter(1, question.getTitle());
			proc.setParameter(2, question.getText());
			proc.setParameter(3, question.getType().toString());
			proc.setParameter(4, bannerID);
			ResultSet results = proc.executeWithResults();
			
			if (null != results)
			{
				while (results.next())
				{
					id = results.getLong(1);
				}
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
			// Logging needed.
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return id;
	}
	
	@Override
	public boolean createQuestionOption(OptionValue option, int order, long questionID) 
	{
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spCreateOptions(?,?,?,?)");
			proc.setParameter(1, questionID);
			proc.setParameter(2, option.getText());
			proc.setParameter(3, option.getStoredAs());
			proc.setParameter(4, order);
			proc.execute();
			
		}
		catch (SQLException e)
		{
			System.out.println(e);
			return false;
			// Logging needed.
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return true;
	}
	
}
