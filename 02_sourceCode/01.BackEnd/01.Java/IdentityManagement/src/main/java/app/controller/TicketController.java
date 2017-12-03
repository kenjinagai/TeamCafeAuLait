package app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.model.response.CoffeeType;
import app.model.response.GetTicketResModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * TicketController
 *
 * @author Shoji Yamada
 *
 */

@RestController
@RequestMapping("tickets")
public class TicketController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "Get Remaining Coffee Ticket.", notes = "Get Remaining Coffee Ticket of Barista and Dolce.")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = { @ApiResponse(code = 403, message = ""),
            @ApiResponse(code = 500, message = "Internal Server Error") })

    public ResponseEntity<GetTicketResModel> getTickets(
            @ApiParam(value = "Authentication token for XSRF.", required = true) @RequestHeader(value = "X-XSRF-TOKEN") final String token) {
        return null;

    }

    @RequestMapping(value = "{name}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete Coffee Ticket.", notes = "Consume an one of the ticket.")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {@ApiResponse(code = 400, message = "チケット残数がありません。"),
            @ApiResponse(code = 404, message = "チケットの種類が見つかりませんでした。")})
    public ResponseEntity<String> DeleteTicket(@ApiParam(value = "Authentication token for XSRF.", required = true)
    @RequestHeader(value = "X-XSRF-TOKEN") final String token, @ApiParam(value = "Consume Coffee Type Tickets")@PathVariable final CoffeeType name, @ApiParam(value = "Number Of Consuming The Coffee Tickets") @RequestParam final Integer num)
    {
        return null;
    }

    @RequestMapping(value = "/Ticket/?buycount = ticketcount", method = RequestMethod.POST)
    @ApiOperation(value = "Buy Coffee Ticket.", notes = "Buy the seven of the tickets.")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = { @ApiResponse(code = 204, message = "コーヒーが選択されていません。")})
    public ResponseEntity<Integer> buyTickets()
    {
        return null;
    }

}
