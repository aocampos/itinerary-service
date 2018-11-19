package aocampos.itinerary.itryturbinemonitoringsvr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableTurbine
@EnableHystrixDashboard
@SpringBootApplication
public class ItryTurbineMonitoringSvrApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItryTurbineMonitoringSvrApplication.class, args);
	}
}
