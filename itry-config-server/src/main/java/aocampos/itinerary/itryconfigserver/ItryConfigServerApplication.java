package aocampos.itinerary.itryconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ItryConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItryConfigServerApplication.class, args);
	}
}
