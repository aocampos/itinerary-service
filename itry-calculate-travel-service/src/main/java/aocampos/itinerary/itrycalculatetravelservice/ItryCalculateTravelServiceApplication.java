package aocampos.itinerary.itrycalculatetravelservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableCircuitBreaker
//@EnableHystrix
@EnableEurekaClient
@EnableFeignClients
@EnableSwagger2
@SpringBootApplication
public class ItryCalculateTravelServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItryCalculateTravelServiceApplication.class, args);
    }
}

