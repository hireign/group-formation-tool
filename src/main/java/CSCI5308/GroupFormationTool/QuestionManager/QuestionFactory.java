package CSCI5308.GroupFormationTool.QuestionManager;

public class QuestionFactory extends QuestionAbstractFactory{

	public IQuestionPersistence createQuestionDB() {
		return new QuestionDB();
	}

	@Override
	public IQuestion createQuestion() {
		return new Question();
	}


	@Override
	public IOptionValue createOptionValue(String text, String storedAs) {
		return new OptionValue(text, storedAs);
	}

	@Override
	public IOptions createOption() {
		return new Options();
	}

}
