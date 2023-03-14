package iss.paf.pafday01;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PafDay01Application {

	private static Logger logger = Logger.getLogger(PafDay01Application.class.getName());

	public static void main(String[] args) {
		// SpringApplication.run(PafDay01Application.class, args);
		SpringApplication app = new SpringApplication(PafDay01Application.class);

		String pwd = System.getenv("PASSWORD");

		if (null != pwd) {

			app.setDefaultProperties(Collections.singletonMap("spring.datasource.password", pwd));

		} else {

			logger.log(Level.INFO, ">>> Missing MySql password");
			System.exit(0);

		}
		app.run(args);
	}

}
