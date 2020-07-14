package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.List;

public interface IOptions {

	void setDefault();

	List<IOptionValue> getOptionList();

	void setOptionList(List<IOptionValue> optionList);

	void addOption();

	void saveOptions(IQuestionPersistence questionDB, long questionID) throws Exception;

	boolean isStringEmpty(String s);

}