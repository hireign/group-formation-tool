package CSCI5308.GroupFormationTool.Security;

public interface IPasswordEncryption {
	public String encryptPassword(String rawPassword);

	public boolean matches(String rawPassword, String encryptedPassword);
}
