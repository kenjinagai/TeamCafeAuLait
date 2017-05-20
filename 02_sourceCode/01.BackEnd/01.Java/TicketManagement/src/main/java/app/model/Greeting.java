package app.model;

import lombok.Data;

@Data
public class Greeting {
	private final long id;
	private final String name;

	public Greeting(long id, String name){
		this.id = id;
		this.name = name;
	}
}
