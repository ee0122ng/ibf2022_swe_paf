package iss.ibf2022.pafworkshopday02;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PafWorkshopDay02Application {

	private static final Logger LOGGER = Logger.getLogger(PafWorkshopDay02Application.class.getName());

	public static void main(String[] args) {
		// SpringApplication.run(PafWorkshopDay02Application.class, args);

		SpringApplication app = new SpringApplication(PafWorkshopDay02Application.class);

		final String PWD = System.getenv("PASSWORD");

		if (null != PWD) {

			app.setDefaultProperties(Collections.singletonMap("spring.datasource.password", PWD));
		} else {

			LOGGER.log(Level.INFO, "Password not set at the environment variable");
		}

		app.run(args);
	}

}
