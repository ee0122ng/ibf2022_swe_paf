package iss.ibf2022.pafworkshopday03;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PafWorkshopDay03Application {

	private static final Logger LOGGER = Logger.getLogger(PafWorkshopDay03Application.class.getName());

	public static void main(String[] args) {
		// SpringApplication.run(PafWorkshopDay03Application.class, args);
		SpringApplication app = new SpringApplication(PafWorkshopDay03Application.class);

		final String PWD = System.getenv("PASSWORD");
		if(null != PWD) {
			app.setDefaultProperties(Collections.singletonMap("spring.datasource.password", PWD));
		} else {
			LOGGER.log(Level.INFO, "Password for mysql is not provided");
			System.exit(0);
		}

		app.run(args);
	}

}
