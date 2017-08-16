package app.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Ticket Model.
 * 
 * This class is used by "Ticket Sum".
 *
 * @author Shoji Yamada
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketModel {
    @ApiModelProperty("Coffee type")
    private CoffeeType coffeeType;
    
    @ApiModelProperty("Ticket of remainig")
    private int remainingNum;
}
