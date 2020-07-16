package CSCI5308.GroupFormationTool.PasswordValidation;

public class MinimumLowercaseValidator extends PasswordValidator {
	public MinimumLowercaseValidator(String constraint) {
		this.constraint = constraint;
	}

	@Override
	public boolean isValid(String password) {
		int minLower = Integer.parseInt(this.constraint);
		int passLower = 0;

		for (int i = 0; i < password.length(); i++) {
			if (Character.isLowerCase(password.charAt(i))) {
				passLower++;
			}
		}

		if (passLower < minLower) {
			return false;
		}

		return true;
	}

	@Override
	public String getValidatorName() {
		return PasswordValidatorType.MINLOWERCASE.toString();
	}

}
