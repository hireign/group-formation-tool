package CSCI5308.GroupFormationTool.PasswordValidation;

public class RestrictedCharacterValidator extends PasswordValidator {

	public RestrictedCharacterValidator(String constraint) {
		this.constraint = constraint;
	}

	@Override
	public boolean isValid(String password) {
		char[] restrictedCharacters = constraint.toCharArray();

		for (int i = 0; i < restrictedCharacters.length; i++) {
			if (password.contains(String.valueOf(restrictedCharacters[i]))) {
				return false;
			}
		}

		return true;
	}

	@Override
	public String getValidatorName() {
		return PasswordValidatorType.RESTRICTEDCHAR.toString();
	}

}
