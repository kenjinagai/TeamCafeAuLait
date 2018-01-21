package app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.entity.ExtendedAuthentication;
import app.model.AuthResult;
import app.model.LoginInfo;
import app.model.response.LoginResponse;
import app.service.AuthenticationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * LoginController.
 *
 * @author Kenji Nagai.
 *
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private AuthenticationService authService;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    /**
     * Login.
     *
     * @param loginInfo user id and password
     * @param HttpServletRequest request
     * @param HttpServletResponse response
     * @return ResponseEntity
     *
     * @author Kenji Nagai.
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "Login with id/passwrod", notes = "# Login with user id and password. \n"
            + "* Return logined user infomation")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = { @ApiResponse(code = 401, message = "Invalid user id or password."),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public ResponseEntity<LoginResponse> login(@RequestBody final LoginInfo loginInfo,
            final HttpServletRequest request,
            final HttpServletResponse response) {
        LoginResponse loginRes = null;
        ResponseEntity<LoginResponse> res = null;
        AuthResult authResult = null;
        try {
            authResult = authService.login(loginInfo);
            // If authentication success, set CSRF in cookie.
            authService.setCsrfCookie(authResult, request, response);
            loginRes = new LoginResponse(authResult);

            res = new ResponseEntity<LoginResponse>(loginRes, null, HttpStatus.CREATED);
        } catch (final AuthenticationException e) {
            // If authentication failed, return unauthrized.
            res = new ResponseEntity<LoginResponse>(null, null, HttpStatus.UNAUTHORIZED);
            LOGGER.error("authError", e.getMessage());
        } catch (final Exception e) {
            // Other exception.
            res = new ResponseEntity<LoginResponse>(null, null,
                    HttpStatus.INTERNAL_SERVER_ERROR);
            LOGGER.error("Exception", e.getMessage());
        }
        return res;
    }

    /**
     * Login with Smart Card.
     *
     * @param HttpServletRequest request
     * @param HttpServletResponse response
     * @return ResponseEntity
     *
     * @author Kenji Nagai.
     */
    @RequestMapping(value = "card", method = RequestMethod.POST)
    @ApiOperation(value = "Login with card", notes = "#Login with a smart card. \n"
            + "* Return logined user infomation")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = { @ApiResponse(code = 401, message = "Not found smart card."),
            @ApiResponse(code = 408, message = "時間内にスマートカードがかざされない場合"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public ResponseEntity<AuthResult> loginSmartCard(final HttpServletRequest request,
            final HttpServletResponse response) {
        ResponseEntity<AuthResult> res = null;
        AuthResult authResult = null;
        try {
            final ExtendedAuthentication extendedAuthentication = new ExtendedAuthentication();
            extendedAuthentication.setExtendedAuthenticationValue(String.valueOf(1));
            authResult = authService.extendedAuthentication(extendedAuthentication);
            // If authentication success, set CSRF in cookie.
            authService.setCsrfCookie(authResult, request, response);
            res = new ResponseEntity<AuthResult>(authResult, null, HttpStatus.CREATED);
        } catch (final AuthenticationException e) {
            // If authentication failed, return unauthrized.
            res = new ResponseEntity<AuthResult>(authResult, null, HttpStatus.UNAUTHORIZED);
            LOGGER.error("authError", e.getMessage());
        } catch (final Exception e) {
            // Other exception.
            res = new ResponseEntity<AuthResult>(authResult, null,
                    HttpStatus.INTERNAL_SERVER_ERROR);
            LOGGER.error("Exception", e.getMessage());
        }
        return res;
    }
}
