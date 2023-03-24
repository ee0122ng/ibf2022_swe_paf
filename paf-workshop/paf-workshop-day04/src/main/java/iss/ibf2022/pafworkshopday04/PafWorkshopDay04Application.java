package iss.ibf2022.pafworkshopday04;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PafWorkshopDay04Application {

	private static final Logger LOGGER = Logger.getLogger(PafWorkshopDay04Application.class.getName());

	public static void main(String[] args) {
		// SpringApplication.run(PafWorkshopDay04Application.class, args);
		SpringApplication app = new SpringApplication(PafWorkshopDay04Application.class);

		final String PWD = System.getenv("PASSWORD");
		if (null != PWD) {
			app.setDefaultProperties(Collections.singletonMap("spring.datasource.password", PWD));
		} else {
			LOGGER.log(Level.INFO, "Password for mysql not provided");
			System.exit(0);
		}

		app.run(args);
	}

}
