package net.robertocrespo.microservices.client.greeting;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Client controller, fetches User info from the microservice via
 * {@link ClientGreetingService}.
 * 
 * @author Roberto Crespo
 */
@Controller
public class ClientGreetingController {

	@Autowired
	protected ClientGreetingService helloWorldService;

	protected Logger logger = Logger.getLogger(ClientGreetingController.class
			.getName());

	//constructor
	public ClientGreetingController(ClientGreetingService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	@RequestMapping("/greeting")
	public String goHome() {
		return "index";
	}
	
	@RequestMapping("/greeting/{name}")
	public String greeting(Model model, @PathVariable("name") String name) {
	
		logger.info("greeting() invoked: " + name);

		Greeting greeting = helloWorldService.greeting(name);
		
		logger.info("greeting() found: " + greeting.getContent());
	
		model.addAttribute("greeting", greeting.getContent());
	
		return "greeting";
		
	}
	
	@RequestMapping("/greetingFuture/{name}")
	public String greetingFuture(Model model, @PathVariable("name") String name) throws InterruptedException, ExecutionException {
	
		logger.info("greetingFuture() invoked: " + name);

		//Greeting greeting = (Greeting) helloWorldService.greetingFuture(name);
		
		Future<Greeting> greeting = helloWorldService.greetingFuture(name);
		
		logger.info("greetingFuture() found: " + greeting.get().getContent());
	
		model.addAttribute("greeting", greeting.get().getContent());
	
		return "greeting";
		
	}
	
}
