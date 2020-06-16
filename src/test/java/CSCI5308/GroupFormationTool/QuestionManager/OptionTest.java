package CSCI5308.GroupFormationTool.QuestionManager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class OptionTest {

	@Test
	public void getIdTest() {
		Option option = new Option();
		option.setId(7);
		Assert.isTrue(option.getId() == 7);
	}

	@Test
	public void setIdTest() {
		Option option = new Option();
		option.setId(7);
		Assert.isTrue(option.getId() == 7);
	}

	@Test
	public void getQuestionIdTest() {
		Option option = new Option();
		option.setQuestionId(7);
		Assert.isTrue(option.getQuestionId() == 7);
	}

	@Test
	public void setQuestionIdTest() {
		Option option = new Option();
		option.setQuestionId(7);
		Assert.isTrue(option.getQuestionId() == 7);
	}

	@Test
	public void getTextTest() {
		Option option = new Option();
		option.setText("CSCI 5308");
		Assert.isTrue(option.getText().equals("CSCI 5308"));
	}

	@Test
	public void setTextTest() {
		Option option = new Option();
		option.setText("CSCI 5308");
		Assert.isTrue(option.getText().equals("CSCI 5308"));
	}
}
