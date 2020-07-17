package CSCI5308.GroupFormationTool.SurveyManager;

public class SurveyFactory extends SurveyAbstractFactory {

	public ISurvey makeSurvey() {
		return new Survey();
	}

	public IResponse makeResponse() {
		return new Response();
	}

	public ISurveyPersistence makeSurveyDB() {
		return new SurveyDB();
	}

	public SurveyIterator makeSurveyIterator() {
		return new CurrentSurvey();
	}

}
