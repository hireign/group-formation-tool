package CSCI5308.GroupFormationTool.PasswordValidation;

public class PasswordFactory extends PasswordAbstractFactory {

	public IPasswordValidatorPersistence makePwdDB() {
		return new PasswordValidatorDB();
	}

	@Override
	public IPasswordValidatorEnumerator makePwdEnumerator(IPasswordValidatorPersistence validatorDB) throws Exception {
		return new PasswordValidatorEnumerator(validatorDB);
	}

}
