package CSCI5308.GroupFormationTool.QuestionManager;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

@SuppressWarnings("deprecation")
class OptionValueTest 
{
	private QuestionAbstractFactory questionFactory = QuestionAbstractFactory.getFactory();
	
	@Test
	public void ConstructorTests() 
	{
		IOptionValue value = questionFactory.createOptionvalue();
		Assert.isTrue(isStringEmpty(value.getText()));
		Assert.isTrue(isStringEmpty(value.getStoredAs()));
	}
	
	@Test
	public void getText() 
	{
		IOptionValue value = questionFactory.createOptionvalue();
		value.setText("Test Text");
		Assert.isTrue(value.getText().equals("Test Text"));
	}
	
	@Test
	public void setText() 
	{
		IOptionValue value = questionFactory.createOptionvalue();
		value.setText("Test Text");
		Assert.isTrue(value.getText().equals("Test Text"));
	}
	
	@Test
	public void getStoredAs() 
	{
		IOptionValue value = questionFactory.createOptionvalue();
		value.setStoredAs("Test");
		Assert.isTrue(value.getStoredAs().equals("Test"));
	}
	
	@Test
	public void setStoredAs() 
	{
		IOptionValue value = questionFactory.createOptionvalue();
		value.setStoredAs("Test");
		Assert.isTrue(value.getStoredAs().equals("Test"));
	}
	
	@Test
	public void createOption() 
	{
		IOptionValue value = questionFactory.createOptionValue("Test Text", "1");
		Assert.isTrue(value.getText().equals("Test Text"));
		Assert.isTrue(value.getStoredAs().equals("1"));
	}
	
	public boolean isStringEmpty(String s) 
	{
		return s.replaceAll(" ","").length() == 0;
	}

}
