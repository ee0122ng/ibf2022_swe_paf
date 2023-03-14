package iss.paf.pafday03;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PafDay03Application {

	private static final Logger logger = Logger.getLogger(PafDay03Application.class.getName());

	public static void main(String[] args) {
		// SpringApplication.run(PafDay03Application.class, args);
		SpringApplication app = new SpringApplication(PafDay03Application.class);

		String pwd = System.getenv("PASSWORD");

		if (null != pwd) {

			app.setDefaultProperties(Collections.singletonMap("spring.datasource.password", pwd));

		} else {

			logger.log(Level.INFO, "PASSWORD not provided");
			System.exit(0);

		}

		app.run(args);
	}

}
