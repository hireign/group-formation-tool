package CSCI5308.GroupFormationTool.Database;

public abstract class DatabaseConfigurationAbstractFactory {

	private static final DefaultDatabaseConfigurationFactory defaultDBConfig = new DefaultDatabaseConfigurationFactory();

	public abstract IDatabaseConfiguration makeDBConfig();

	public static DatabaseConfigurationAbstractFactory getFactory() {
		return defaultDBConfig;
	}
}
