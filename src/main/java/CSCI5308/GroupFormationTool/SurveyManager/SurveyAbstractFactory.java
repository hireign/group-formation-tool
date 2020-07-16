package CSCI5308.GroupFormationTool.SurveyManager;

public abstract class SurveyAbstractFactory {

	private static final SurveyFactory surveyFactory = new SurveyFactory();

	public static SurveyAbstractFactory getFactory() {
		return surveyFactory;
	}

	public abstract ISurvey makeSurvey();

	public abstract IResponse makeResponse();

	public abstract ISurveyPersistence makeSurveyDB();

	public abstract SurveyIterator makeSurveyIterator();
}
