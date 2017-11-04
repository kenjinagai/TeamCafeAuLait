package app.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Aggregation Model.
 * 
 * This class is used by "AggregationController".
 *
 * @author Shoji Yamada
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AggregationModel {
    @ApiModelProperty("Coffee type")
    private CoffeeType name;
    
    @ApiModelProperty("Consumed tickets count")
    private Integer consumedCount;
    
    @ApiModelProperty("Consumed price count")
    private Integer consumedPrice;
}
