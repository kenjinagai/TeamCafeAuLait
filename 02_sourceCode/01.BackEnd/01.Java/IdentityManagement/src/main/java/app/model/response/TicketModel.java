package app.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Ticket Model.
 *
 * This class is used by "Ticket Controller".
 *
 * @author Shoji Yamada
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketModel {
    @ApiModelProperty("Remainig tickets count")
    private Integer remainingCount;

    //This property doesn't need because "Ticket Controller" isn't used.
    //@ApiModelProperty("Used tickets count")
    //private Integer useCount;
}
