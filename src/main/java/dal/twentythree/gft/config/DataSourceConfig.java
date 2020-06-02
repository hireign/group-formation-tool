package dal.twentythree.gft.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {
	@Bean(name="dataSource")
	@Primary
	@ConfigurationProperties("datasource.sql")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
}
