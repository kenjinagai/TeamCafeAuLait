package app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.model.response.RemainingTicketResModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("ticket")
public class TicketController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "Get Remaining Coffee Ticket.", notes = "Get Remaining Coffee Ticket of Barista and Dolce.")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = { @ApiResponse(code = 403, message = "Invalid user id or password."),
            @ApiResponse(code = 500, message = "Internal Server Error") })

    public ResponseEntity<RemainingTicketResModel> login(
            @ApiParam(value = "Authentication token for XSRF.", required = true) @RequestHeader(value = "X-XSRF-TOKEN") final String token) {
        return null;

    }

}
