package net.robertocrespo.microservices.client.greeting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home page controller.
 * 
 * @author Roberto Crespo
 */
@Controller
public class ClientGreetingHomeController {

	@RequestMapping("/")
	public String home() {
		return "index";
	}

}
