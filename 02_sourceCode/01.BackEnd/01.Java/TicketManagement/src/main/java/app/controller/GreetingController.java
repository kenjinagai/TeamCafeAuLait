package app.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.model.Greeting;
import app.service.GreetingService;

@RestController
public class GreetingController {
	private final AtomicLong counter = new AtomicLong();

	@Autowired
	GreetingService greetingService;

	@RequestMapping("/greeting")
	public ResponseEntity<Greeting> greeting(@RequestParam(value="name", defaultValue="World") String name){
		return new ResponseEntity<Greeting>(greetingService.getGreet(counter, name), HttpStatus.OK);
	}
}
