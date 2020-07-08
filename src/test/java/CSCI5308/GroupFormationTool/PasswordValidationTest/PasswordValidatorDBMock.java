package CSCI5308.GroupFormationTool.PasswordValidationTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import CSCI5308.GroupFormationTool.PasswordValidation.IPasswordValidatorPersistence;
import CSCI5308.GroupFormationTool.PasswordValidation.PasswordValidatorType;

public class PasswordValidatorDBMock implements IPasswordValidatorPersistence
{
	@Override
	public HashMap<Long, String> loadActivePasswordValidators() 
	{
		HashMap<Long,String> activeValidators = new HashMap<Long, String>();
		activeValidators.put((long) 1, PasswordValidatorType.MINLENGTH.toString());
		activeValidators.put((long) 2, PasswordValidatorType.MAXLENGTH.toString());
		return activeValidators;
	}

	@Override
	public String loadConstraintByValidatorId(long id) 
	{
		if(id<=6) 
		{
			return "5";
		}else 
		{
			return ".,#*";
		}
	}

	@Override
	public List<String> fetchPreviousPasswordsByBannerID(String bannerID, int constraint) 
	{
		List<String> passwordList = new ArrayList<String>();
		if(bannerID.equals("B1234567")) 
		{
			passwordList.add("fail");
		}else
		{
			passwordList.add("other");
		}
		
		for(int i=0;i<constraint;i++) 
		{
			passwordList.add("password"+i);
		}
		return passwordList;
	}

}
