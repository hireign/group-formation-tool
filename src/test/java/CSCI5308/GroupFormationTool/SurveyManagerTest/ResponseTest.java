package CSCI5308.GroupFormationTool.SurveyManagerTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.TestSystemConfig;
import CSCI5308.GroupFormationTool.SurveyManager.IResponse;
import CSCI5308.GroupFormationTool.SurveyManager.ISurveyPersistence;
import CSCI5308.GroupFormationTool.SurveyManager.SurveyAbstractFactory;

@SuppressWarnings("deprecation")
public class ResponseTest {

	private SurveyAbstractFactory surveyFactory = SurveyAbstractFactory.getFactory();

	@Test
	public void getId() {
		IResponse response = surveyFactory.createResponse();
		response.setId(1);
		Assert.isTrue(1 == response.getId());
	}

	@Test
	public void setId() {
		IResponse response = surveyFactory.createResponse();
		response.setId(1);
		Assert.isTrue(1 == response.getId());
	}

	@Test
	public void getQuestionID() {
		IResponse response = surveyFactory.createResponse();
		response.setQuestionID(1);
		Assert.isTrue(1 == response.getQuestionID());
	}

	@Test
	public void setQuestionID() {
		IResponse response = surveyFactory.createResponse();
		response.setQuestionID(1);
		Assert.isTrue(1 == response.getQuestionID());
	}

	@Test
	public void getUserID() {
		IResponse response = surveyFactory.createResponse();
		response.setUserID(1);
		Assert.isTrue(1 == response.getUserID());
	}

	@Test
	public void setUserID() {
		IResponse response = surveyFactory.createResponse();
		response.setUserID(1);
		Assert.isTrue(1 == response.getUserID());
	}

	@Test
	public void getResponse() {
		IResponse response = surveyFactory.createResponse();
		response.setResponse("1");
		Assert.isTrue("1" == response.getResponse());
	}

	@Test
	public void setResponse() {
		IResponse response = surveyFactory.createResponse();
		response.setResponse("1");
		Assert.isTrue("1" == response.getResponse());
	}

	@Test
	public void getSurveyID() {
		IResponse response = surveyFactory.createResponse();
		response.setSurveyID(1);
		Assert.isTrue(1 == response.getSurveyID());
	}

	@Test
	public void setSurveyID() {
		IResponse response = surveyFactory.createResponse();
		response.setSurveyID(1);
		Assert.isTrue(1 == response.getSurveyID());
	}

	@Test
	public void getOptions() {
		IResponse response = surveyFactory.createResponse();
		List<String> options = new ArrayList<String>();
		options.add("1");
		response.setOptions(options);
		Assert.isTrue("1" == response.getOptions().get(0));
	}

	@Test
	public void setOptions() {
		IResponse response = surveyFactory.createResponse();
		List<String> options = new ArrayList<String>();
		options.add("1");
		response.setOptions(options);
		Assert.isTrue("1" == response.getOptions().get(0));
	}

	@Test
	public void convertOptionListToResponseString() {
		IResponse response = surveyFactory.createResponse();
		response.setResponse("1");
		Assert.isTrue("1" == response.getResponse());
	}

	@Test
	public void save() throws Exception {
		ISurveyPersistence surveyDB = TestSystemConfig.instance().getSurveyDB();
		IResponse response = surveyFactory.createResponse();
		response.setId(1);
		response.setUserID(1);
		response.setQuestionID(1);
		response.setResponse("1");
		surveyDB.saveSurveyResponse(response);
		Assert.isTrue(1 == response.getId());
	}
}
