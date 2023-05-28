package tobyspring.helloboot;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class HelloController {

	private ApplicationContext applicationContext;

	private final HelloService helloService;

	public HelloController(HelloService helloService, ApplicationContext applicationContext) {
		this.helloService = helloService;
		this.applicationContext = applicationContext;
	}

	@GetMapping("/hello")
	public String hello(String name) {
		return helloService.sayHello(Objects.requireNonNull(name));
	}
}
