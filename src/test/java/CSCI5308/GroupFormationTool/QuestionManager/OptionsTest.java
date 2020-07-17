package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.TestSystemConfig;

@SuppressWarnings("deprecation")
class OptionsTest 
{	
	private QuestionAbstractFactory questionFactory = QuestionAbstractFactory.getFactory();
	
	@Test
	public void ConstructorTests() 
	{
		IOptions options = questionFactory.makeOptions();
		Assert.isTrue(options.getOptionList().size() == 0);
	}
	
	@Test
	public void getOptionList() 
	{
		IOptions options = questionFactory.makeOptions();
		List<IOptionValue> list = new ArrayList<IOptionValue>();
		list.add(questionFactory.makeOptionValue("test","test"));
		options.setOptionList(list);
		Assert.isTrue(options.getOptionList() == list);
		
	}

	@Test
	public void setOptionList() 
	{
		IOptions options = questionFactory.makeOptions();
		List<IOptionValue> list = new ArrayList<IOptionValue>();
		list.add(questionFactory.makeOptionValue("test","test"));
		options.setOptionList(list);
		Assert.isTrue(options.getOptionList() == list);
	}

	@Test
	public void addOption() 
	{
		IOptions options = questionFactory.makeOptions();
		options.addOption();
		Assert.isTrue(options.getOptionList().size()>0);
	}
	
	@Test
	public void saveOptions() throws Exception 
	{
		IOptionValue option = new OptionValue("Test Text", "1");
		IQuestionPersistence questionDB = TestSystemConfig.instance().getQuestionDB();
		boolean status = questionDB.createQuestionOption(option, 1, 1);
		Assert.isTrue(status == true);
		status = questionDB.createQuestionOption(option, 1, -1);
		Assert.isTrue(status == false);
		option = new OptionValue("", "");
		status = questionDB.createQuestionOption(option, 1, 1);
		Assert.isTrue(status == false);
	}
				
}
