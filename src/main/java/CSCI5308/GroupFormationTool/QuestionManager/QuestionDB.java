package CSCI5308.GroupFormationTool.QuestionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.LoggerInterface;
import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class QuestionDB implements IQuestionPersistence {

	private static LoggerInterface logger = SystemConfig.instance().getLogger();

	@Override
	public List<IQuestion> loadQuestionsSortedByTitle(String bannerID) throws SQLException {
		List<IQuestion> questionList = new ArrayList<IQuestion>();
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spFindSortedQuestionsByTitle(?)");
			proc.setParameter(1, bannerID);
			ResultSet results = proc.executeWithResults();
			IQuestion question;

			if (null != results) {
				while (results.next()) {
					long id = results.getLong(1);
					String title = results.getString(2);
					String text = results.getString(3);
					QuestionType type = QuestionType.valueOf(results.getString(4).toUpperCase());
					Timestamp timestamp = results.getTimestamp(5);

					question = QuestionAbstractFactory.getFactory().makeQuestion();
					question.setId(id);
					question.setTitle(title);
					question.setText(text);
					question.setType(type);
					question.setTimestamp(timestamp);
					questionList.add(question);
				}
				logger.info(QuestionDB.class.toString(),
						String.format("action=loadQuestionsSortedByTitle status=success"));
			}
		} catch (SQLException e) {
			logger.error(QuestionDB.class.toString(), String
					.format("action=loadQuestionsSortedByTitle status=failure" + " exception=%s", e.getMessage()));
			throw e;

		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return questionList;
	}

	@Override
	public List<IQuestion> loadSortedQuestionsSortedByDate(String bannerID) throws SQLException {
		List<IQuestion> questionList = new ArrayList<IQuestion>();
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spFindSortedQuestionsByDate(?)");
			proc.setParameter(1, bannerID);
			ResultSet results = proc.executeWithResults();
			IQuestion question;

			if (null != results) {
				while (results.next()) {
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
				logger.info(QuestionDB.class.toString(),
						String.format("action=loadSortedQuestionsSortedByDate status=success"));
			}
		} catch (SQLException e) {
			logger.error(QuestionDB.class.toString(), String
					.format("action=loadSortedQuestionsSortedByDate status=failure" + " exception=%s", e.getMessage()));
			throw e;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return questionList;
	}

	@Override
	public boolean deleteQuestionByQuestionId(long questionID) throws SQLException {
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spDeleteQuestionsByQuestionID(?)");
			proc.setParameter(1, questionID);
			proc.execute();
			logger.info(QuestionDB.class.toString(), String.format("questionID=%d action= status=success", questionID));

			return true;
		} catch (SQLException e) {
			logger.error(QuestionDB.class.toString(), String
					.format("questionID=%d action= status=success" + " exception=%s", questionID, e.getMessage()));
			throw e;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
	}

	@Override
	public long createQuestion(IQuestion question, String bannerID) throws SQLException {
		long id = -1;
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spCreateQuestion(?,?,?,?)");
			proc.setParameter(1, question.getTitle());
			proc.setParameter(2, question.getText());
			proc.setParameter(3, question.getType().toString());
			proc.setParameter(4, bannerID);
			ResultSet results = proc.executeWithResults();

			if (null != results) {
				while (results.next()) {
					id = results.getLong(1);
				}
				logger.info(QuestionDB.class.toString(),
						String.format("title= %s action=createQuestion status=success", question.getTitle()));
			}
		} catch (SQLException e) {
			logger.error(QuestionDB.class.toString(),
					String.format("title= %s action=createQuestion status=failure" + " exception=%s",
							question.getTitle(), e.getMessage()));
			throw e;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return id;
	}

	@Override
	public boolean createQuestionOption(IOptionValue option, int order, long questionID) throws SQLException {
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spCreateOptions(?,?,?,?)");
			proc.setParameter(1, questionID);
			proc.setParameter(2, option.getText());
			proc.setParameter(3, option.getStoredAs());
			proc.setParameter(4, order);
			proc.execute();
			logger.info(QuestionDB.class.toString(),
					String.format("action=createQuestionOption " + "id = %d", questionID));

			return true;

		} catch (SQLException e) {
			logger.error(QuestionDB.class.toString(), String.format(
					"action=createQuestionOption question" + "id = %d" + " exception=%s", questionID, e.getMessage()));
			throw e;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
	}

}
