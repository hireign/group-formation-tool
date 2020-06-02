package CSCI5308.ADV_SDC.GFT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,SecurityAutoConfiguration.class })
@ComponentScan({"controller"})
public class CopyCatMeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CopyCatMeApplication.class, args);
	}

}
