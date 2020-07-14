package CSCI5308.GroupFormationTool.PasswordValidation;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.IUser;

public interface IPasswordValidatorEnumerator 
{
	public List<PasswordValidator> getActiveValidators(IUser user) throws Exception;
}
