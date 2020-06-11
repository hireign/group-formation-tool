package CSCI5308.GroupFormationTool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class GroupFormationToolApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroupFormationToolApplication.class, args);
	}

}
