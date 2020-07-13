package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.ArrayList;
import java.util.List;
//wrapper class to pass lists from thymeleaf to controllers
public class Options 
{
	public List<OptionValue> optionList;
	
	public Options() 
	{
		setDefault();
	}
	
	public void setDefault() 
	{
		optionList = new ArrayList<OptionValue>();
	}
	
	public List<OptionValue> getOptionList() 
	{
		return optionList;
	}

	public void setOptionList(List<OptionValue> optionList) 
	{
		this.optionList = optionList;
	}

	public void addOption() 
	{
		String index = String.valueOf(optionList.size()+1);
		optionList.add(new OptionValue(index, index));
	}
	
	public void saveOptions(IQuestionPersistence questionDB, long questionID) throws Exception 
	{
		int order=1;
		if(questionID!=-1) {
			for (OptionValue option: optionList) 
			{
				String text = option.getText();
		        String storedAs = option.getStoredAs();
		        if(isStringEmpty(text) || isStringEmpty(storedAs)) 
		        {
		        	continue;
		        }
		        questionDB.createQuestionOption(option, order++, questionID);
			}
		}
				
	}
	
	public boolean isStringEmpty(String s) 
	{
		return s.replaceAll(" ","").length() == 0;
	}
	
}
