package app.service;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import app.constant.TicketConstants;
import app.model.Greeting;
@Service
public class GreetingService {
	private static Logger log = LoggerFactory.getLogger(GreetingService.class);

	public Greeting getGreet(AtomicLong counter, String name){
		Greeting res = new Greeting(counter.incrementAndGet(), String.format(TicketConstants.TEMPLATE, name));
		log.info("Greeting: ", res);
		return res;
	}
}
