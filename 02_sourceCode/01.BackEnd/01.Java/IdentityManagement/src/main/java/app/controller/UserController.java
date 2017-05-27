package app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.entity.User;
import app.repository.UserRepository;
import app.service.AuthenticationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * UserController
 *
 * @author Kenji Nagai.
 *
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationService authService;

    /**
     * Select all user.
     * Only admin user can used.
     *
     * @param String token
     * @param HttpServletRequest request for authrization.
     * @return ResponseEntity
     * @author Kenji Nagai.
     */
    @ApiOperation(value = "Get users infomation", notes = "Get users infomation. "
            + "<br>This endpoint is allowed to call by Admin.")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = { @ApiResponse(code = 403, message = "Invalid X-XSRF-TOKEN."),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public ResponseEntity<List<User>> findAll(
            @ApiParam(value = "Authentication token for XSRF.", required = true) @RequestHeader(value = "X-XSRF-TOKEN") final String token,
            final HttpServletRequest request) {
        if (!authService.isAdmin(request)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<List<User>>(repository.findAll(), HttpStatus.OK);
    }
}
