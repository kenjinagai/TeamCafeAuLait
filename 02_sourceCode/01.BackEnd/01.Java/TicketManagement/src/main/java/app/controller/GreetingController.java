package app.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.model.Greeting;
import app.service.GreetingService;
import app.service.db.TestDbService;

@RestController
public class GreetingController {
	private final AtomicLong counter = new AtomicLong();
	private static Logger log = LoggerFactory.getLogger(GreetingController.class);

	@Autowired
	GreetingService greetingService;

	@Autowired
	TestDbService sqlService;

	@RequestMapping("/greeting")
	public ResponseEntity<Greeting> greeting(@RequestParam(value="name", defaultValue="World") String name){
		return new ResponseEntity<Greeting>(greetingService.getGreet(counter, name), HttpStatus.OK);
	}

	@RequestMapping("/select_test")
	public ResponseEntity<List<Map<String, Object>>> selectTest(@RequestParam(value="name", defaultValue="World") String name){
		return new ResponseEntity<List<Map<String, Object>>>(sqlService.home(), HttpStatus.OK);
	}
}
