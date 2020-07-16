package CSCI5308.GroupFormationTool.Security;

public class EncryptionFactory extends EncryptionAbstractFactory {

	@Override
	public IPasswordEncryption makeEncrypter() {
		return new BCryptPasswordEncryption();
	}

}
