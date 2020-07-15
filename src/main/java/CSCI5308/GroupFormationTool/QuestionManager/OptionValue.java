package CSCI5308.GroupFormationTool.QuestionManager;

public class OptionValue implements IOptionValue 
{
	String text;
	String storedAs;
	
	public OptionValue() 
	{
		setDefault();
	}
	
	public OptionValue(String text, String storedAs) 
	{
		createOption(text, storedAs);
	}

	@Override
	public void setDefault() 
	{
		text="";
		storedAs="";
	}
	
	@Override
	public String getText() 
	{
		return text;
	}
	
	@Override
	public void setText(String text) 
	{
		this.text = text;
	}
	
	@Override
	public String getStoredAs() 
	{
		return storedAs;
	}
	
	@Override
	public void setStoredAs(String storedAs) 
	{
		this.storedAs = storedAs;
	}
	
	@Override
	public IOptionValue createOption(String text, String storedAs) 
	{
		this.text = text;
		this.storedAs = storedAs;
		return this;
	}
	
}
