package CSCI5308.GroupFormationTool.PasswordValidation;

public abstract class PasswordValidator 
{
	public String constraint;
	public abstract boolean isValid(String password);
	public abstract String getValidatorName();
}
