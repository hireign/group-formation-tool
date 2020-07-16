package CSCI5308.GroupFormationTool.PasswordValidation;

public class MaximumLengthValidator extends PasswordValidator {
	public MaximumLengthValidator(String constraint) {
		this.constraint = constraint;
	}

	@Override
	public boolean isValid(String password) {
		int maxLength = Integer.parseInt(this.constraint);
		int passLength = password.length();

		if (passLength > maxLength) {
			return false;
		}
		return true;
	}

	@Override
	public String getValidatorName() {
		return PasswordValidatorType.MAXLENGTH.toString();
	}

}
