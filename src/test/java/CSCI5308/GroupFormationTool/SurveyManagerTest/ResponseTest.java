package CSCI5308.GroupFormationTool.SurveyManagerTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.SurveyManager.ISurveyPersistence;
import CSCI5308.GroupFormationTool.SurveyManager.Response;

@SuppressWarnings("deprecation")
public class ResponseTest {

	@Test
	public void getId() {
		Response response = new Response();
		response.setId(1);
		Assert.isTrue(1 == response.getId());
	}

	@Test
	public void setId() {
		Response response = new Response();
		response.setId(1);
		Assert.isTrue(1 == response.getId());
	}

	@Test
	public void getQuestionID() {
		Response response = new Response();
		response.setQuestionID(1);
		Assert.isTrue(1 == response.getQuestionID());
	}

	@Test
	public void setQuestionID() {
		Response response = new Response();
		response.setQuestionID(1);
		Assert.isTrue(1 == response.getQuestionID());
	}

	@Test
	public void getUserID() {
		Response response = new Response();
		response.setUserID(1);
		Assert.isTrue(1 == response.getUserID());
	}

	@Test
	public void setUserID() {
		Response response = new Response();
		response.setUserID(1);
		Assert.isTrue(1 == response.getUserID());
	}

	@Test
	public void getResponse() {
		Response response = new Response();
		response.setResponse("1");
		Assert.isTrue("1" == response.getResponse());
	}

	@Test
	public void setResponse() {
		Response response = new Response();
		response.setResponse("1");
		Assert.isTrue("1" == response.getResponse());
	}

	@Test
	public void getSurveyID() {
		Response response = new Response();
		response.setSurveyID(1);
		Assert.isTrue(1 == response.getSurveyID());
	}

	@Test
	public void setSurveyID() {
		Response response = new Response();
		response.setSurveyID(1);
		Assert.isTrue(1 == response.getSurveyID());
	}

	@Test
	public void getOptions() {
		Response response = new Response();
		List<String> options = new ArrayList<String>();
		options.add("1");
		response.setOptions(options);
		Assert.isTrue("1" == response.getOptions().get(0));
	}

	@Test
	public void setOptions() {
		Response response = new Response();
		List<String> options = new ArrayList<String>();
		options.add("1");
		response.setOptions(options);
		Assert.isTrue("1" == response.getOptions().get(0));
	}

	@Test
	public void convertOptionListToResponseString() {
		Response response = new Response();
		response.setResponse("1");
		Assert.isTrue("1" == response.getResponse());
	}

	@Test
	public void save() {
		ISurveyPersistence surveyDB = new SurveyDBMock();
		Response response = new Response();
		response.setId(1);
		response.setUserID(1);
		response.setQuestionID(1);
		response.setResponse("1");
		Assert.isTrue(surveyDB.saveSurveyResponse(response));
	}
}
