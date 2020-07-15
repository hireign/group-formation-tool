package CSCI5308.GroupFormationTool.SurveyManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.LoggerInterface;
import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.UserDB;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.QuestionManager.IOptionValue;
import CSCI5308.GroupFormationTool.QuestionManager.IOptions;
import CSCI5308.GroupFormationTool.QuestionManager.Question;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionAbstractFactory;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionType;

public class SurveyDB implements ISurveyPersistence
{
	private static LoggerInterface logger = SystemConfig.instance().getLogger();

	public ISurvey loadSurveyByCourseID(long courseID) throws Exception
	{
		ISurvey survey = null;
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spLoadSurveyQuestions(?)");
			proc.setParameter(1, courseID);
			ResultSet results = proc.executeWithResults();
			Question question;
			List<Question> questions = new ArrayList<Question>();
			long surveyID = 0;
			int active = 0;
			Timestamp createdAt = null;
			
			if (null != results)
			{
				while (results.next())
				{
					surveyID = results.getLong(1);
					active = results.getInt(3);
					createdAt = results.getTimestamp(4);
					long questionID = results.getLong(2);
					String questionType = results.getString(5);
					String questionTitle = results.getString(6);
					String questionText = results.getString(7);
					Timestamp questionCreatedAt = results.getTimestamp(8);
					
					question = new Question();
					question.setId(questionID);
					question.setType(QuestionType.valueOf(questionType.toUpperCase()));
					question.setTitle(questionTitle);
					question.setText(questionText);
					question.setTimestamp(questionCreatedAt);
					question.setOptions(this.loadOptionsByQuestionID(questionID));
					questions.add(question);
				}
				
				if(!results.next()) {
					survey = SurveyAbstractFactory.getFactory().createSurvey();
					survey.setId(surveyID);
					survey.setActive(active);
					survey.setCreatedAt(createdAt);
					survey.setQuestions(questions);
				}
			}
		}
		catch (SQLException e)
		{
			logger.error(UserDB.class.toString(),String.format("userID=%d action=loadSurveyByCourseID status=failure exception e=%s", courseID,e.getMessage()));
			throw e;
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return survey;
	}
	
	public IOptions loadOptionsByQuestionID(long questionID) throws Exception
	{
		List<IOptionValue> optionList = new ArrayList<IOptionValue>();
		IOptions options = QuestionAbstractFactory.getFactory().createOptions();
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spFindOptionsByQuestionID(?)");
			proc.setParameter(1, questionID);
			ResultSet results = proc.executeWithResults();
			IOptionValue optionValue;
			
			if (null != results)
			{
				while (results.next())
				{
					String displayText = results.getString(1);
					String storedAs = results.getString(2);
					
					optionValue = QuestionAbstractFactory.getFactory().createOptionvalue();
					optionValue.setText(displayText);
					optionValue.setStoredAs(storedAs);
					optionList.add(optionValue);
				}
				
				options.setOptionList(optionList);
			}
		}
		catch (SQLException e)
		{
			logger.error(UserDB.class.toString(),String.format("userID=%d action=loadOptionsByQuestionID status=failure exception e=%s", questionID,e.getMessage()));
			throw e;
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return options;
	}

	@Override
	public void saveSurveyResponse(IResponse surveyResponse) throws SQLException {
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spCreateResponse(?, ?, ?, ?, ?)");
			proc.setParameter(1, surveyResponse.getId());
			proc.setParameter(2, surveyResponse.getQuestionID());
			proc.setParameter(3, surveyResponse.getUserID());
			proc.setParameter(4, surveyResponse.getResponse());
			proc.setParameter(5, surveyResponse.getSurveyID());
			proc.execute();
		}
		catch (SQLException e)
		{
			logger.error(UserDB.class.toString(),String.format("userID=%d action=saveSurveyResponse status=failure exception e=%s", surveyResponse.getId(),e.getMessage()));
			throw e;
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
	}

	@Override
	public boolean deleteSurveyQuestion(long questionID, long courseID) throws SQLException {
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spDeleteQuestionFromSurvey(?,?)");
			proc.setParameter(1, questionID);
			proc.setParameter(2, courseID);
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

}
