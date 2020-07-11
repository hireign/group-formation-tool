package CSCI5308.GroupFormationTool.PasswordValidation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.AccessControl.User;

public class PasswordValidatorEnumerator implements IPasswordValidatorEnumerator
{	
	private IPasswordValidatorPersistence validatorDB;
	private List<PasswordValidator> activeValidators;
	private HashMap<Long, String> validators;
	
	public PasswordValidatorEnumerator(IPasswordValidatorPersistence validatorDB) throws Exception 
	{
		this.validatorDB = validatorDB;
		activeValidators = new ArrayList<PasswordValidator>();
		validators = validatorDB.loadActivePasswordValidators();
	}

	public List<PasswordValidator> getActiveValidators(User user) throws Exception
	{
		System.out.println("Validators active: \n"+validators.values());
		activeValidators = new ArrayList<PasswordValidator>();
		for (@SuppressWarnings("rawtypes") Map.Entry item : validators.entrySet()) 
		{
			long key = (long) item.getKey();
			String value = (String) item.getValue();
			String constraint = validatorDB.loadConstraintByValidatorId(key);
	        
			if(value.equals(PasswordValidatorType.MINLENGTH.toString())) 
	        {
	        	activeValidators.add(new MinimumLengthValidator(constraint));
	        }else if(value.equals(PasswordValidatorType.MAXLENGTH.toString())) 
	        {
	        	activeValidators.add(new MaximumLengthValidator(constraint));
	        }else if(value.equals(PasswordValidatorType.MINUPPERCASE.toString())) 
	        {
	        	activeValidators.add(new MinimumUppercaseValidator(constraint));
	        }else if(value.equals(PasswordValidatorType.MINLOWERCASE.toString())) 
	        {
	        	activeValidators.add(new MinimumLowercaseValidator(constraint));
	        }else if(value.equals(PasswordValidatorType.MINSYMBOLS.toString())) 
	        {
	        	activeValidators.add(new MinimumSymbolValidator(constraint));
	        }else if(value.equals(PasswordValidatorType.RESTRICTEDCHAR.toString())) 
	        {
	        	activeValidators.add(new RestrictedCharacterValidator(constraint));
	        }else if(value.equals(PasswordValidatorType.PASSWORDHISTORY.toString())) 
	        {
	        	activeValidators.add(new PasswordHistoryValidator(constraint, user));
	        }   
		}
		
		return activeValidators;
	}
	
}
