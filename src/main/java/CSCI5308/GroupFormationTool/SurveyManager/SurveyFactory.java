package CSCI5308.GroupFormationTool.SurveyManager;

public class SurveyFactory extends SurveyAbstractFactory{

	@Override
	public ISurvey createSurvey() {
		return new Survey();
	}

	@Override
	public IResponse createResponse() {
		return new Response();
	}

	@Override
	public ISurveyPersistence createSurveyDB() {
		return new SurveyDB();
	}

}
