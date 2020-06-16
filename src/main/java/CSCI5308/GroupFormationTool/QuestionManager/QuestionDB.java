package CSCI5308.GroupFormationTool.QuestionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class QuestionDB implements IQuestionPersistence {

	@Override
	public List<Question> loadAllQuestionsByInstructor(int instructorId) {
		CallStoredProcedure proc = null;
		ResultSet rs = null, rsOptions = null;
		List<Question> questions = new ArrayList<Question>();

		try {
			proc = new CallStoredProcedure("spLoadAllQuestionsByInstructor(?)");
			proc.setParameter(1, instructorId);
			rs = proc.executeWithResults();
			if (null != rs) {
				while (rs.next()) {
					Question question = new Question();
					long questionId = rs.getLong(1);
					question.setId(questionId);
					question.setTitle(rs.getString(2));
					question.setText(rs.getString(3));
					question.setType(rs.getString(4));
					question.setDate(rs.getDate(5));

					proc = new CallStoredProcedure("spLoadAllOptionsByQuestion(?)");
					proc.setParameter(1, questionId);
					rsOptions = proc.executeWithResults();

					List<Option> options = new ArrayList<Option>();
					if (null != rsOptions) {
						while (rsOptions.next()) {
							Option option = new Option();
							option.setQuestionId(questionId);
							option.setText(rsOptions.getString(1));
							option.setValue(rsOptions.getInt(2));
							options.add(option);
						}
					}

					question.setOptions(options);
					questions.add(question);
				}
			}
		} catch (SQLException e) {
			return null;
		} finally {
			try {
			if (null != rsOptions) {
				rsOptions.close();
			}
			if (null != rs) {
				rs.close();
			}
			if (null != proc) {
				proc.cleanup();
			}
			} catch(Exception e) {}
		}
		return questions;
	}

	@Override
	public boolean create(Question question) {
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spCreateQuestion(?, ?, ?, ?, ?)");
			proc.setParameter(1, question.getTitle());
			proc.setParameter(2, question.getText());
			proc.setParameter(3, question.getType());
			proc.setParameter(4, question.getInstructorId());
			proc.registerOutputParameterLong(5);
			proc.execute();

			long questionId = proc.getReturnedValueLong(1);

			for (Option option : question.getOptions()) {
				proc = new CallStoredProcedure("spCreateOption(?, ?, ?)");
				proc.setParameter(1, questionId);
				proc.setParameter(2, option.getText());
				proc.setParameter(3, option.getValue());
				proc.execute();
			}
		} catch (SQLException e) {
			return false;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return true;
	}

	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
