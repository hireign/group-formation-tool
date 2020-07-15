package CSCI5308.GroupFormationTool.SurveyManager;

public class SurveyFactory extends SurveyAbstractFactory{

	public ISurvey createSurvey() {
		return new Survey();
	}

	public IResponse createResponse() {
		return new Response();
	}

	public ISurveyPersistence createSurveyDB() {
		return new SurveyDB();
	}
	
	public SurveyIterator createSurveyIterator() {
		return new CurrentSurvey();
	}

}
