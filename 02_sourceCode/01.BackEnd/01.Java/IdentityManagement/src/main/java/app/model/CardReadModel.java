package app.model;

import lombok.Data;

/**
 * Card read model.
 *
 * @author Kenji Nagai
 *
 */
@Data
public class CardReadModel {
    private final String id;

    public CardReadModel(final String id) {
        this.id = id;
    }
}
