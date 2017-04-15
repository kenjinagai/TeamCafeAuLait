package app.model;

import lombok.Getter;

@Getter
public class Greeting {
	@Getter
	private final long id;
	private final String name;

	public Greeting(long id, String name){
		this.id = id;
		this.name = name;
	}
}
