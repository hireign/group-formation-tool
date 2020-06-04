package util;

//Interface for PwdEncryption
public interface IPasswordEncryption
{
	// For SignUp
	public String encryptPassword(String rawPassword);
	
	// For Login
	public boolean matches(String rawPassword, String encryptedPassword);
}
