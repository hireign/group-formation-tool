package CSCI5308.GroupFormationTool.QuestionManager;

import java.sql.Timestamp;

public interface IQuestion {

	void setDefaults();

	Timestamp getTimestamp();

	void setTimestamp(Timestamp timestamp);

	long getId();

	void setId(long id);

	String getTitle();

	void setTitle(String title);

	String getText();

	void setText(String text);

	QuestionType getType();

	void setType(QuestionType type);

	void deleteQuestion(IQuestionPersistence questionDB, long questionID) throws Exception;

	long createQuestion(IQuestionPersistence questionDB, String bannerID) throws Exception;

	void setOptions(IOptions loadOptionsByQuestionID);

}