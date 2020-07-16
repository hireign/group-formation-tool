package CSCI5308.GroupFormationTool.SurveyManagerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import CSCI5308.GroupFormationTool.QuestionManager.QuestionAbstractFactory;
import CSCI5308.GroupFormationTool.SurveyManager.SurveyAbstractFactory;
import CSCI5308.GroupFormationTool.SurveyManager.SurveyManagerController;

@WebMvcTest(SurveyManagerController.class)
public class SurveyManagerControllerTest {
	@Autowired
	private MockMvc mockMvc;
	private SurveyAbstractFactory surveyFactory = SurveyAbstractFactory.getFactory();
	private QuestionAbstractFactory questionFactory = QuestionAbstractFactory.getFactory();
	
	@Test
	public void loadSurvey() throws Exception {
		mockMvc.perform(get("/survey?courseID=1")).andDo(print()).andExpect(status().is3xxRedirection());
	}
	
	@Test
	public void displayQuestion() throws Exception {
		mockMvc.perform(get("/survey/submit")).andExpect(status().isOk()).andExpect(view().name("survey/displayquestion"));
	}
	
	@Test
	public void displayNextQuestion() throws Exception {
		mockMvc.perform(get("/survey/submit").param("nextQuestion", "true")).andExpect(status().isOk()).andExpect(view().name("survey/displayquestion"));
	}
}
