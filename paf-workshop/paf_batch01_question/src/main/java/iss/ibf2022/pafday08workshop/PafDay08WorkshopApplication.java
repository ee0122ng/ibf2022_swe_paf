package iss.ibf2022.pafday08workshop;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PafDay08WorkshopApplication implements CommandLineRunner {

	private static final Logger LOGGER = Logger.getLogger(PafDay08WorkshopApplication.class.getName());
	
	public static void main(String[] args) {
		
		SpringApplication app = new SpringApplication(PafDay08WorkshopApplication.class);

		final String PWD = System.getenv("MYSQL_PWD");
		if (null != PWD) {
			app.setDefaultProperties(Collections.singletonMap("spring.datasource.password", PWD));
		} else {
			LOGGER.log(Level.INFO, "Password for mysql user not provided");
			System.exit(0);
		}

		app.run(args);
	}

	@Override
	public void run(String... args) {

	}

}
