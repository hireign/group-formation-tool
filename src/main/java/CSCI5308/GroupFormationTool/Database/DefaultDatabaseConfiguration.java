package CSCI5308.GroupFormationTool.Database;

public class DefaultDatabaseConfiguration implements IDatabaseConfiguration
{
//	private static final String URL = System.getenv("jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_23_DEVINT?autoreconnect=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
//	private static final String USER = System.getenv("CSCI5308_23_DEVINT_USER");
//	private static final String PASSWORD = System.getenv("CSCI5308_23_DEVINT_23272");
	private static final String URL = System.getenv("URL");
	private static final String USER = System.getenv("USER");
	private static final String PASSWORD = System.getenv("PASSWORD");

	public String getDatabaseUserName()
	{
		return USER;
	}

	public String getDatabasePassword()
	{
		return PASSWORD;
	}

	public String getDatabaseURL()
	{
		return URL;
	}
}
