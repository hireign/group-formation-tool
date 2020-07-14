package CSCI5308.GroupFormationTool.PasswordValidation;

public class PasswordFactory extends PasswordAbstractFactory{

	public IPasswordValidatorPersistence createPwdDB() {
		return new PasswordValidatorDB();
	}

	@Override
	public IPasswordValidatorEnumerator createPwdEnumerator(IPasswordValidatorPersistence validatorDB) throws Exception {
		return new PasswordValidatorEnumerator(validatorDB);
	}

}
