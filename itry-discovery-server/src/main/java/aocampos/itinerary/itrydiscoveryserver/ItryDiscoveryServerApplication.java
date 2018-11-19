package aocampos.itinerary.itrydiscoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ItryDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItryDiscoveryServerApplication.class, args);
	}
}
