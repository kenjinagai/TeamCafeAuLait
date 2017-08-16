package app.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemainingTicketResModel {
    @ApiModelProperty("Barista ticket information of user")
    private TicketModel baristaTicket;

    @ApiModelProperty("Dolce ticket information of user")
    private TicketModel dolceTicket;
}
