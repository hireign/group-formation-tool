package dal.twentythree.gft.util;

public interface Constants {
	public static final String selectCourses = "SELECT * from Course";
	public static final String selectUsers = "SELECT * from User, UserContactInfo where User.id = UserContactInfo.userID;";
	
	public static final String BANNERID = "bannerID";
	public static final String EMAILID = "email";
	public static final String ID = "userID";
	public static final String FIRSTNAME = "firstName";
	public static final String LASTNAME = "lastName";
	public static final String PASSWORD = "password";
	
	public static final String COURSEID = "id";
	public static final String COURSENAME = "title";
	
	public static final String ROLEID = "id";
	public static final String ROLEENAME = "role";
	
//	public static final String DatabaseURL = "jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_23_DEVINT?autoreconnect = true";
//	public static final String DatabaseUserName = "CSCI5308_23_DEVINT_USER";
//	public static final String DatabasePassword = "CSCI5308_23_DEVINT_23272";
	
	public static final String DatabaseDriver = "com.mysql.cj.jdbc.Driver";
	public static final String DatabaseURL = "jdbc:mysql://localhost:3306/local_program?autoreconnect = true";
	public static final String DatabaseUserName = "root";
	public static final String DatabasePassword = "root";
}
