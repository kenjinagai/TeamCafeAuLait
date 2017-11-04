package app.model.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Response Model for GET /tickets
 * 
 * @author Shoji Yamada
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAggregationResModel {
    private List<AggregationModel> buytickets;

    private List<AggregationModel> allbuytickets;
}
