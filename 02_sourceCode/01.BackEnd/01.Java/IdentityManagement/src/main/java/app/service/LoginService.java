package app.service;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import app.model.AuthResult;
import app.model.LoginInfo;
import app.model.LoginUserDetail;

/**
 * Login Service.
 * This is implemented with Spring Security.
 *
 * @author Kenji Nagai
 *
 */
@Service
public class LoginService {

	@Autowired
	private AuthenticationManager authMgr;
	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

	/**
	 * Login.
	 *
	 * @param loginInfo User Authentication Parameter.
	 * @return User Information.
	 * @throws AuthenticationException
	 * @throws Exception
	 */
    public AuthResult login(LoginInfo loginInfo) throws AuthenticationException, Exception {
        // Authentication with username and password.
        Authentication request = new UsernamePasswordAuthenticationToken(loginInfo.getUserId(), loginInfo.getPassword());
        Authentication authentication = authMgr.authenticate(request);
        // Set context as authentication.
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Get user data.
        LoginUserDetail principal = (LoginUserDetail)authentication.getPrincipal();
        return new AuthResult(principal.getUsername(), principal.getPermissionList(), principal.getAuthorities().stream().map(authority -> authority.getAuthority()).collect(Collectors.toList()));
    }
}