package uk.me.ruthmills.batbox.boiler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BoilerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoilerApplication.class, args);
	}
}
