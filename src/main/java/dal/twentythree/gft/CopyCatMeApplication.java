package dal.twentythree.gft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"dal.twentythree"})
public class CopyCatMeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CopyCatMeApplication.class, args);
	}

}
