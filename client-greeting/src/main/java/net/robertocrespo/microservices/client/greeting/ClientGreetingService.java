package net.robertocrespo.microservices.client.greeting;


import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


/**
 * Hide the access to the microservice inside this local service.
 * 
 * @author Roberto Crespo
 */
@Service
public class ClientGreetingService {

	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;

	protected String serviceUrl;

	protected Logger logger = Logger.getLogger(ClientGreetingService.class
			.getName());

	public ClientGreetingService(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl: "http://" + serviceUrl;
	}

	/**
	 * The RestTemplate works because it uses a custom request-factory that uses
	 * Ribbon to look-up the service to use. This method simply exists to show
	 * this.
	 */
	@PostConstruct
	public void demoOnly() {
		// Can't do this in the constructor because the RestTemplate injection
		// happens afterwards.
		logger.warning("The RestTemplate request factory is "
				+ restTemplate.getRequestFactory());
	}

	@HystrixCommand(fallbackMethod = "greetingDefault")
	public Greeting greeting (String name) {	

		logger.info("greeting() invoked: for " + name);
		
		Greeting greeting =  restTemplate.getForObject(serviceUrl + "/greeting/{name}",Greeting.class, name);
	
		return greeting;		
	}
	
	public Greeting greetingDefault(String name) {
	    return new Greeting("Hello World thanks to Circuit Breaker (Hystrix)");
	  }
	
}
