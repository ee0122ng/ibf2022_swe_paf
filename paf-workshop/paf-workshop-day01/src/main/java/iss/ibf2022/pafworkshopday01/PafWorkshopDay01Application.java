package iss.ibf2022.pafworkshopday01;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PafWorkshopDay01Application {

	@Value("${spring.datasource.username}")
	private static String username;

	private static final Logger LOGGER = Logger.getLogger(PafWorkshopDay01Application.class.getName());

	public static void main(String[] args) {
		// SpringApplication.run(PafWorkshopDay01Application.class, args);

		SpringApplication app = new SpringApplication(PafWorkshopDay01Application.class);

		final String PWD = System.getenv("PASSWORD");

		if (null != PWD) {

			app.setDefaultProperties(Collections.singletonMap("spring.datasource.password", PWD));

		} else {

			LOGGER.log(Level.INFO, "Password for username = %s not provided".formatted(username));
			System.exit(0);
		}

		app.run(args);
	}

}
