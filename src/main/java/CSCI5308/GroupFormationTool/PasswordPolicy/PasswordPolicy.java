package CSCI5308.GroupFormationTool.PasswordPolicy;

public class PasswordPolicy{
	
	private int id;
	private int Minimum_length;
	private int Maximum_length;
	private int Minimum_uppercase_Chars;
	private int Minimum_lowercase_Chars;
	private int Minimum_special_characters;
	private String Disallowed_Chars;
	private int Password_History_Constraint_No;
	private boolean Enable_Policy;

	public PasswordPolicy(){
		
	}
	
	public PasswordPolicy(IPasswordPolicyPersistence passwordPolicy){
		passwordPolicy.populatePasswordPolicy(this);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMinimum_length() {
		return Minimum_length;
	}
	public void setMinimum_length(int minimum_length) {
		Minimum_length = minimum_length;
	}
	public int getMaximum_length() {
		return Maximum_length;
	}
	public void setMaximum_length(int maximum_length) {
		Maximum_length = maximum_length;
	}
	public int getMinimum_uppercase_Chars() {
		return Minimum_uppercase_Chars;
	}
	public void setMinimum_uppercase_Chars(int minimum_uppercase_Chars) {
		Minimum_uppercase_Chars = minimum_uppercase_Chars;
	}
	public int getMinimum_lowercase_Chars() {
		return Minimum_lowercase_Chars;
	}
	public void setMinimum_lowercase_Chars(int minimum_lowercase_Chars) {
		Minimum_lowercase_Chars = minimum_lowercase_Chars;
	}
	public int getMinimum_special_characters() {
		return Minimum_special_characters;
	}
	public void setMinimum_special_characters(int minimum_special_characters) {
		Minimum_special_characters = minimum_special_characters;
	}
	public String getDisallowed_Chars() {
		return Disallowed_Chars;
	}
	public void setDisallowed_Chars(String disallowed_Chars) {
		Disallowed_Chars = disallowed_Chars;
	}
	public int getPassword_History_Constraint_No() {
		return Password_History_Constraint_No;
	}
	public void setPassword_History_Constraint_No(int password_History_Constraint_No) {
		Password_History_Constraint_No = password_History_Constraint_No;
	}
	public boolean isEnable_Policy() {
		return Enable_Policy;
	}
	public void setEnable_Policy(boolean enable_Policy) {
		Enable_Policy = enable_Policy;
	}
	
}
