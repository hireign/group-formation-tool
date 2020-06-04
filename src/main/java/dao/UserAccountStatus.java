package dao;

public class UserAccountStatus {
	private String email;
	private String confirmationToken;

	public UserAccountStatus(String email, String confirmationToken) {
		super();
		this.email = email;
		this.confirmationToken = confirmationToken;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}
}
