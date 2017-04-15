package app.service;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import app.constant.TicketConstants;
import app.model.Greeting;

@Service
public class GreetingService {
	public Greeting getGreet(AtomicLong counter, String name){
		return new Greeting(counter.incrementAndGet(), String.format(TicketConstants.TEMPLATE, name));
	}
}
