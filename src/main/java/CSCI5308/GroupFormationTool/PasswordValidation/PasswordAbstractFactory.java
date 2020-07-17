package CSCI5308.GroupFormationTool.PasswordValidation;

public abstract class PasswordAbstractFactory {

	private static final PasswordFactory pwdDBFactory = new PasswordFactory();

	public abstract IPasswordValidatorPersistence makePwdDB();

	public abstract IPasswordValidatorEnumerator makePwdEnumerator(IPasswordValidatorPersistence validatorDB)
			throws Exception;

	public static PasswordAbstractFactory getFactory() {
		return pwdDBFactory;
	}

}
