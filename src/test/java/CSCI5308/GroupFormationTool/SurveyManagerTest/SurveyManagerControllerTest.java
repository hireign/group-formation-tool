package CSCI5308.GroupFormationTool.SurveyManagerTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import CSCI5308.GroupFormationTool.SurveyManager.SurveyManagerController;

@WebMvcTest(SurveyManagerController.class)
public class SurveyManagerControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SurveyManagerController surveyManagerController;

	@Test
	public void greetingShouldReturnMessageFromService() throws Exception {
//		mockMvc.perform(post("/forums/{forumId}/register", 42L)
//		        .contentType("application/json")
//		        .param("sendWelcomeMail", "true")
//		        .content(objectMapper.writeValueAsString(user)))
//		        .andExpect(status().isOk());
	}
}
