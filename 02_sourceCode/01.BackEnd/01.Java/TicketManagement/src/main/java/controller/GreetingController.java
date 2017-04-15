package controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Greeting;
import service.GreetingService;

@RestController
public class GreetingController {
	private final AtomicLong counter = new AtomicLong();

	@Autowired
	GreetingService greetingService;

	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name){
		return greetingService.getGreet(counter, name);
	}
}
