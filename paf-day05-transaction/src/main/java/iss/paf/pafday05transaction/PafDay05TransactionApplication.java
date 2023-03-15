package iss.paf.pafday05transaction;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PafDay05TransactionApplication {

	private static final Logger LOGGER = Logger.getLogger(PafDay05TransactionApplication.class.getName());

	public static void main(String[] args) {
		// SpringApplication.run(PafDay05TransactionApplication.class, args);
		SpringApplication app = new SpringApplication(PafDay05TransactionApplication.class);

		// check if password provided
		String pwd = System.getenv("PASSWORD");

		if (null != pwd) {

			app.setDefaultProperties(Collections.singletonMap("spring.datasource.password", pwd));

		} else {

			LOGGER.log(Level.INFO, ">>> Password not provided");
			System.exit(0);

		}
	}

}
