package CSCI5308.GroupFormationTool.QuestionManager;

public abstract class QuestionAbstractFactory {
	
	private static final QuestionFactory QuestionDBFactoryObj = new QuestionFactory();
	
	public abstract IQuestionPersistence createQuestionDB();
	
	public abstract IQuestion createQuestion();
	
	public abstract IOptionValue createOptionValue(String text,String storedAs);
	
	public abstract IOptionValue createOptionvalue();
	
	public abstract IOptions createOptions();
	
	public static QuestionFactory getFactory() {
		return QuestionDBFactoryObj;
	}

}
