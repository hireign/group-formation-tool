package CSCI5308.GroupFormationTool.QuestionManager;

public abstract class QuestionAbstractFactory {

	private static final QuestionFactory QuestionDBFactoryObj = new QuestionFactory();

	public abstract IQuestionPersistence makeQuestionDB();

	public abstract IQuestion makeQuestion();

	public abstract IOptionValue makeOptionValue(String text, String storedAs);

	public abstract IOptionValue makeOptionValue();

	public abstract IOptions makeOptions();

	public static QuestionFactory getFactory() {
		return QuestionDBFactoryObj;
	}

}
