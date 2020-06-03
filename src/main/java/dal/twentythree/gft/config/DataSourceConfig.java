package dal.twentythree.gft.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import dal.twentythree.gft.util.Constants;

@Configuration
public class DataSourceConfig {
	public static DataSource dataSource = null;
	private static String dbDriver = Constants.DatabaseDriver;
	private static String dbURL = Constants.DatabaseURL;
	private static String dbUser = Constants.DatabaseUserName;
	private static String dbPassword = Constants.DatabasePassword;
	
	@Bean
	@Primary
	public static DataSource dataSource() {
		if(dataSource == null) {
			dataSource = DataSourceBuilder.create().username(dbUser).password(dbPassword).url(dbURL).driverClassName(dbDriver).build();
		}
		return dataSource;
	}
}
