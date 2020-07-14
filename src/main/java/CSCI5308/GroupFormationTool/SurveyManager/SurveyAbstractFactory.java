package CSCI5308.GroupFormationTool.SurveyManager;

public abstract class SurveyAbstractFactory {

	private static final SurveyFactory surveyFac = new SurveyFactory();
	
	public abstract ISurvey createSurvey();
	public abstract IResponse createResponse();
	public abstract ISurveyPersistence createSurveyDB();
	
	public static SurveyAbstractFactory getFactory() {
		return surveyFac;
	}
}
