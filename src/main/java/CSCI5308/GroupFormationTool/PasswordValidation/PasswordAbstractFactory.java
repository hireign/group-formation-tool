package CSCI5308.GroupFormationTool.PasswordValidation;

public abstract class PasswordAbstractFactory {
	
	private static final PasswordFactory pwdDBFactory = new PasswordFactory();
	
	public abstract IPasswordValidatorPersistence createPwdDB();
	
	public abstract IPasswordValidatorEnumerator createPwdEnumerator(IPasswordValidatorPersistence validatorDB) throws Exception;
	
	public static PasswordAbstractFactory getFactory() {
		return pwdDBFactory;
	}

}
