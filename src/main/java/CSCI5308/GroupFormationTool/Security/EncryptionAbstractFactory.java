package CSCI5308.GroupFormationTool.Security;

public abstract class EncryptionAbstractFactory {

	private static final EncryptionFactory EFactory = new EncryptionFactory();

	public abstract IPasswordEncryption makeEncrypter();

	public static EncryptionAbstractFactory getFactory() {
		return EFactory;
	}

}
