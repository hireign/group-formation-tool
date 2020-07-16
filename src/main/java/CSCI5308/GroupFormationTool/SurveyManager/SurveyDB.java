package CSCI5308.GroupFormationTool.SurveyManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.LoggerInterface;
import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.QuestionManager.IOptionValue;
import CSCI5308.GroupFormationTool.QuestionManager.IOptions;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionAbstractFactory;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionType;

public class SurveyDB implements ISurveyPersistence {
	private static LoggerInterface logger = SystemConfig.instance().getLogger();
	private QuestionAbstractFactory questionFactory = QuestionAbstractFactory.getFactory();

	public ISurvey loadSurveyByCourseID(long courseID) throws Exception {
		ISurvey survey = null;
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spLoadSurveyQuestions(?)");
			proc.setParameter(1, courseID);
			ResultSet results = proc.executeWithResults();
			IQuestion question;
			List<IQuestion> questions = new ArrayList<IQuestion>();
			long surveyID = 0;
			int active = 0;
			Timestamp createdAt = null;
			boolean fetched = false;
			
			if (null != results) {
				while (results.next()) {
					fetched = true;
					surveyID = results.getLong(1);
					active = results.getInt(3);
					createdAt = results.getTimestamp(4);
					long questionID = results.getLong(2);
					String questionType = results.getString(5);
					String questionTitle = results.getString(6);
					String questionText = results.getString(7);
					Timestamp questionCreatedAt = results.getTimestamp(8);

					question = questionFactory.createQuestion();
					question.setId(questionID);
					question.setType(QuestionType.valueOf(questionType.toUpperCase()));
					question.setTitle(questionTitle);
					question.setText(questionText);
					question.setTimestamp(questionCreatedAt);

					if (question.getType() == QuestionType.MCQMULTIPLE || question.getType() == QuestionType.MCQONE) {
						question.setOptions(loadOptionsByQuestionID(questionID));
					} else {
						question.setOptions(null);
					}

					questions.add(question);
				}

				if (!results.next() && fetched) {
					survey = SurveyAbstractFactory.getFactory().createSurvey();
					survey.setId(surveyID);
					survey.setActive(active);
					survey.setCreatedAt(createdAt);
					survey.setQuestions(questions);
				}
			}
		} catch (SQLException e) {
			logger.error(SurveyDB.class.toString(), String.format(
					"courseID=%d action=loadSurveyByCourseID status=failure exception e=%s", courseID, e.getMessage()));
			throw e;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return survey;
	}

	public IOptions loadOptionsByQuestionID(long questionID) throws Exception {
		List<IOptionValue> optionList = new ArrayList<IOptionValue>();
		IOptions options = QuestionAbstractFactory.getFactory().createOptions();
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spFindOptionsByQuestionID(?)");
			proc.setParameter(1, questionID);
			ResultSet results = proc.executeWithResults();
			IOptionValue optionValue;

			if (null != results) {
				while (results.next()) {
					String displayText = results.getString(1);
					String storedAs = results.getString(2);

					optionValue = QuestionAbstractFactory.getFactory().createOptionvalue();
					optionValue.setText(displayText);
					optionValue.setStoredAs(storedAs);
					optionList.add(optionValue);
				}

				options.setOptionList(optionList);
			}
		} catch (SQLException e) {
			logger.error(SurveyDB.class.toString(),
					String.format("questionID=%d action=loadOptionsByQuestionID status=failure exception e=%s",
							questionID, e.getMessage()));
			throw e;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return options;
	}

	@Override
	public void saveSurveyResponse(IResponse surveyResponse) throws SQLException {
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spCreateResponse(?, ?, ?, ?, ?)");
			proc.setParameter(1, surveyResponse.getId());
			proc.setParameter(2, surveyResponse.getQuestionID());
			proc.setParameter(3, surveyResponse.getUserID());
			proc.setParameter(4, surveyResponse.getResponse());
			proc.setParameter(5, surveyResponse.getSurveyID());
			proc.execute();
		} catch (SQLException e) {
			logger.error(SurveyDB.class.toString(),
					String.format("surveyResponseID=%d action=saveSurveyResponse status=failure exception e=%s",
							surveyResponse.getId(), e.getMessage()));
			throw e;
		} finally {
			if (null != proc) {
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
		} catch (SQLException e) {
			logger.error(SurveyDB.class.toString(),
					String.format("questionID=%d action=deleteQuestion status=failure to delete question e=%s",
							questionID, e.getMessage()));
			throw e;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return true;
	}

	public boolean addSurveyQuestion(long questionID, long courseID, long instructorID) throws Exception {
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spAddQuestionToSurvey(?,?,?)");
			proc.setParameter(1, questionID);
			proc.setParameter(2, courseID);
			proc.setParameter(3, instructorID);
			proc.execute();
		} catch (SQLException e) {
			logger.error(SurveyDB.class.toString(),
					String.format("questionID=%d action=addQuestion status=failure to add question e=%s", questionID,
							e.getMessage()));
			throw e;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return true;
	}

	public boolean publishSurvey(long courseID) throws Exception{
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spPublishSurvey(?)");
			proc.setParameter(1, courseID);
			proc.execute();
		} catch (Exception e) {
			logger.error(SurveyDB.class.toString(),
					String.format("courseID=%d action=publishSurvey status=failure to publish survey e=%s", courseID,
							e.getMessage()));
			throw e;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return true;
	}
}
