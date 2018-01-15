package app.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Ticket Set Model.
 *
 * This class is used by "Ticket Controller".
 *
 * @author Shoji Yamada
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketSetModel {
    @ApiModelProperty("Ticket set count")
    private Integer buyCount;

    @ApiModelProperty("Ticket set price")
    private Integer buyPrice;
}
