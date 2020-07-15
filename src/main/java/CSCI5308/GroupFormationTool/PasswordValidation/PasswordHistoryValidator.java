package CSCI5308.GroupFormationTool.PasswordValidation;

import java.util.List;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;

public class PasswordHistoryValidator extends PasswordValidator
{
	IUser user;
	
	public PasswordHistoryValidator(String constraint, IUser user) 
	{
		this.constraint = constraint;
		this.user = user;
	}
	
	@Override
	public boolean isValid(String password) throws Exception 
	{
		IPasswordValidatorPersistence validatorDB = new PasswordValidatorDB();
		IPasswordEncryption passwordEncryption = SystemConfig.instance().getPasswordEncryption();
		
		List<String> previousPass = validatorDB.fetchPreviousPasswordsByBannerID(user.getBannerID(), Integer.parseInt(constraint));
		for(int i=0; i<previousPass.size(); i++)
		{
			if(passwordEncryption.matches(password, previousPass.get(i))) 
			{
				return false;
			}
		}
		
		return true;
	}

	@Override
	public String getValidatorName() 
	{
		return PasswordValidatorType.PASSWORDHISTORY.toString();
	}
}
