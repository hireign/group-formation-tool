package CSCI5308.GroupFormationTool.QuestionManager;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

@SuppressWarnings("deprecation")
class OptionValueTest 
{
	@Test
	public void ConstructorTests() 
	{
		IOptionValue value = new OptionValue();
		Assert.isTrue(isStringEmpty(value.getText()));
		Assert.isTrue(isStringEmpty(value.getStoredAs()));
	}
	
	@Test
	public void getText() 
	{
		IOptionValue value = new OptionValue();
		value.setText("Test Text");
		Assert.isTrue(value.getText().equals("Test Text"));
	}
	
	@Test
	public void setText() 
	{
		IOptionValue value = new OptionValue();
		value.setText("Test Text");
		Assert.isTrue(value.getText().equals("Test Text"));
	}
	
	@Test
	public void getStoredAs() 
	{
		IOptionValue value = new OptionValue();
		value.setStoredAs("Test");
		Assert.isTrue(value.getStoredAs().equals("Test"));
	}
	
	@Test
	public void setStoredAs() 
	{
		IOptionValue value = new OptionValue();
		value.setStoredAs("Test");
		Assert.isTrue(value.getStoredAs().equals("Test"));
	}
	
	@Test
	public void createOption() 
	{
		IOptionValue value = new OptionValue("Test Text", "1");
		Assert.isTrue(value.getText().equals("Test Text"));
		Assert.isTrue(value.getStoredAs().equals("1"));
	}
	
	public boolean isStringEmpty(String s) 
	{
		return s.replaceAll(" ","").length() == 0;
	}

}
