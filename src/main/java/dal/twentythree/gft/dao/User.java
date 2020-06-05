package dal.twentythree.gft.dao;

public class User {
	private String bannerId;
	private String password;
	private Integer enabled;
	
	public User(String bannerId, String password, Integer enabled) {
		super();
		this.bannerId = bannerId;
		this.password = password;
		this.enabled = enabled;
	}
	public String getBannerId() {
		return bannerId;
	}
	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
}
