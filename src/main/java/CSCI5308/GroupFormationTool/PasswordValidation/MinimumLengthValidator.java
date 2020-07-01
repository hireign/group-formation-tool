package CSCI5308.GroupFormationTool.PasswordValidation;

public class MinimumLengthValidator extends PasswordValidator 
{
	public  MinimumLengthValidator(String constraint) 
	{
		this.constraint = constraint;
	}
	
	@Override
	public boolean isValid(String password) 
	{
		int minLength = Integer.parseInt(this.constraint);
		int passLength = password.length();
		
		if(passLength<minLength) 
		{
			return false;
		}
		return true;
	}
	
	@Override
	public String getValidatorName() 
	{
		return PasswordValidatorType.MINLENGTH.toString();
	}
	
}
