package CSCI5308.GroupFormationTool.Database;

public class DefaultDatabaseConfigurationFactory extends DatabaseConfigurationAbstractFactory {

	public IDatabaseConfiguration createDBConfig() {
		return new DefaultDatabaseConfiguration();
	}

}
