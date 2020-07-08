package CSCI5308.GroupFormationTool.PasswordValidation;

public class MinimumUppercaseValidator extends PasswordValidator 
{
	public MinimumUppercaseValidator(String constraint) 
	{
		this.constraint = constraint;
	}
	
	@Override
	public boolean isValid(String password) 
	{
		int minUpper = Integer.parseInt(this.constraint);
		int passUpper = 0;
		
		for (int i=0; i<password.length(); i++)
		{
			if (Character.isUpperCase(password.charAt(i))) 
			{
				passUpper++;
			}
		}
		
		if(passUpper<minUpper) 
		{
			return false;
		}
		
		return true;
	}
	
	@Override
	public String getValidatorName() 
	{
		return PasswordValidatorType.MINUPPERCASE.toString();
	}
	
}
