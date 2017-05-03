package app.model;

import lombok.Data;

@Data
public class CardReadModel {
	private String id;

	public CardReadModel(String id){
		this.id = id;
	}
}
