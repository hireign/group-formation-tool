package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

@SuppressWarnings("deprecation")
class OptionsTest 
{	
	@Test
	public void ConstructorTests() 
	{
		IOptions options = new Options();
		Assert.isTrue(options.getOptionList().size() == 0);
	}
	
	@Test
	public void getOptionList() 
	{
		IOptions options = new Options();
		List<IOptionValue> list = new ArrayList<IOptionValue>();
		list.add(new OptionValue("test","test"));
		options.setOptionList(list);
		Assert.isTrue(options.getOptionList() == list);
		
	}

	@Test
	public void setOptionList() 
	{
		IOptions options = new Options();
		List<IOptionValue> list = new ArrayList<IOptionValue>();
		list.add(new OptionValue("test","test"));
		options.setOptionList(list);
		Assert.isTrue(options.getOptionList() == list);
	}

	@Test
	public void addOption() 
	{
		IOptions options = new Options();
		options.addOption();
		Assert.isTrue(options.getOptionList().size()>0);
	}
	
	@Test
	public void saveOptions() throws Exception 
	{
		IOptionValue option = new OptionValue("Test Text", "1");
		IQuestionPersistence questionDB = new QuestionDBMock();
		boolean status = questionDB.createQuestionOption(option, 1, 1);
		Assert.isTrue(status == true);
		status = questionDB.createQuestionOption(option, 1, -1);
		Assert.isTrue(status == false);
		option = new OptionValue("", "");
		status = questionDB.createQuestionOption(option, 1, 1);
		Assert.isTrue(status == false);
	}
				
}
