package CSCI5308.GroupFormationTool.Database;

public class DefaultDatabaseConfigurationFactory extends DatabaseConfigurationAbstractFactory {

	public IDatabaseConfiguration makeDBConfig() {
		return new DefaultDatabaseConfiguration();
	}

}
