package dal.twentythree.gft.config;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;

import dal.twentythree.gft.exception.CopyCatMeDBConfigException;
import dal.twentythree.gft.util.Constants;

@Configuration
public class DBConfig implements Constants{
	@Resource
	private DataSource dataSource;
	private static DBConfig dbConfigInstance = null;
	
	public Connection getConnectionInstance() throws CopyCatMeDBConfigException{
		try {
			return dataSource.getConnection();
		}
		catch(SQLException e) {
			throw new CopyCatMeDBConfigException();
		}

	}

	public static DBConfig getDBConfigInstance() {
		if (null == dbConfigInstance) {
			dbConfigInstance = new DBConfig();
		}
		return dbConfigInstance;
	}

}
