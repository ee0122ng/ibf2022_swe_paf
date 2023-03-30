package ibf2022.batch2.paf.serverstub;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerstubApplication {

	private static final Logger LOGGER = Logger.getLogger(ServerstubApplication.class.getName());

	public static void main(String[] args) {
		// SpringApplication.run(ServerstubApplication.class, args);

		SpringApplication app = new SpringApplication(ServerstubApplication.class);

		final String PWD = System.getenv("MYSQL_PASSWORD");
		final String RAILWAY_PWD = System.getenv("RAILWAY_PASSWORD");

		if (null != RAILWAY_PWD) {

			app.setDefaultProperties(Collections.singletonMap("spring.datasource.password", RAILWAY_PWD));

		} else if (null != PWD) {

			app.setDefaultProperties(Collections.singletonMap("spring.datasource.password", PWD));

		} else {

			LOGGER.log(Level.INFO, "No password provided for mysql login");
		}

		app.run(args);
	}

}
