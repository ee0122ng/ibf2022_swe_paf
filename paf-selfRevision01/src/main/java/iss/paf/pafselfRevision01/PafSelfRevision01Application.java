package iss.paf.pafselfRevision01;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PafSelfRevision01Application {

	public static void main(String[] args) {
		// SpringApplication.run(PafSelfRevision01Application.class, args);

		SpringApplication app = new SpringApplication(PafSelfRevision01Application.class);

		// check if password is set via environment variable
		String pwd = System.getenv("PASSWORD");
		app.setDefaultProperties(Collections.singletonMap("spring.datasource.password", pwd));
		app.run(args);
	}

}
