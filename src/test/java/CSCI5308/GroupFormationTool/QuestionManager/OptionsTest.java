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
		IOptions options = questionFactory.createOptions();
		Assert.isTrue(options.getOptionList().size() == 0);
	}
	
	@Test
	public void getOptionList() 
	{
		IOptions options = questionFactory.createOptions();
		List<IOptionValue> list = new ArrayList<IOptionValue>();
		list.add(questionFactory.createOptionValue("test","test"));
		options.setOptionList(list);
		Assert.isTrue(options.getOptionList() == list);
		
	}

	@Test
	public void setOptionList() 
	{
		IOptions options = questionFactory.createOptions();
		List<IOptionValue> list = new ArrayList<IOptionValue>();
		list.add(questionFactory.createOptionValue("test","test"));
		options.setOptionList(list);
		Assert.isTrue(options.getOptionList() == list);
	}

	@Test
	public void addOption() 
	{
		IOptions options = questionFactory.createOptions();
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
