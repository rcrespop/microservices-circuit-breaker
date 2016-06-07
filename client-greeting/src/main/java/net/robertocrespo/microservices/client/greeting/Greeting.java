package net.robertocrespo.microservices.client.greeting;


import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Greeting DTO - used to interact with the {@link ClientGreetingService}.
 * 
 * @author Roberto Crespo
 */
@JsonRootName("greeting")
public class Greeting {

  
    protected String content;
    
    /**
	 * Default constructor 
	 */
	protected Greeting() {
		this.content = "Hello!";
	}

    public Greeting(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
