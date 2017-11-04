package app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.model.response.GetAggregationResModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * AggregationController
 *
 * @author Shoji Yamada
 *
 */

@RestController
@RequestMapping("aggregation")
public class AggregationController {
    @RequestMapping(value = "tickets", method = RequestMethod.GET)
    @ApiOperation(value = "Get Aggregation.", notes = "Get Aggregation of each user.")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = { @ApiResponse(code = 400, message = "範囲が適切ではありません。")})
    public GetAggregationResModel SearchAggregation(@ApiParam(value = "Authentication token for XSRF.", required = true) @RequestHeader(value = "X-XSRF-TOKEN") final String token
            ,@ApiParam(value = "Aggregation Start Day(yyyymmdd).", required = true) @RequestParam final String start,@ApiParam(value = "Aggregation End Day(yyyymmdd).", required = true) @RequestParam final String end
            ,final HttpServletRequest request)
    {
        return null;
    }
}
