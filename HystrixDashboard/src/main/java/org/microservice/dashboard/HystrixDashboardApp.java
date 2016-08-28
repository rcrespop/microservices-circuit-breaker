package org.microservice.dashboard;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;


/**
 * All you need to run a Hystrix Dashboard.
 * 
 * @author Roberto Crespo
 */

@SpringBootApplication
@EnableHystrixDashboard
@EnableTurbine
public class HystrixDashboardApp {

	
	public static void main(String[] args) {

		SpringApplication.run(HystrixDashboardApp.class, args);

	}
}
