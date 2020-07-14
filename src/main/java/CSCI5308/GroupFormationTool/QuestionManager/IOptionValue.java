package CSCI5308.GroupFormationTool.QuestionManager;

public interface IOptionValue {

	void setDefault();

	String getText();

	void setText(String text);

	String getStoredAs();

	void setStoredAs(String storedAs);

	IOptionValue createOption(String text, String storedAs);

}