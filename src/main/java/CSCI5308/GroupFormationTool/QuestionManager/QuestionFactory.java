package CSCI5308.GroupFormationTool.QuestionManager;

public class QuestionFactory extends QuestionAbstractFactory {

	public IQuestionPersistence makeQuestionDB() {
		return new QuestionDB();
	}

	@Override
	public IQuestion makeQuestion() {
		return new Question();
	}

	@Override
	public IOptionValue makeOptionValue(String text, String storedAs) {
		return new OptionValue(text, storedAs);
	}

	@Override
	public IOptions makeOptions() {
		return new Options();
	}

	@Override
	public IOptionValue makeOptionvalue() {
		return new OptionValue();
	}

}
