package app.service;

import java.util.stream.Collectors;

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

    /**
     * Login.
     *
     * @param loginInfo user id and password
     * @return User Authentication Parameter
     * @throws AuthenticationException authentication failure
     * @throws Exception internal error
     */
    public AuthResult login(final LoginInfo loginInfo) throws AuthenticationException, Exception {
        // Authentication with username and password.
        final Authentication request = new UsernamePasswordAuthenticationToken(
                loginInfo.getUserId(),
                loginInfo.getPassword());
        final Authentication authentication = authMgr.authenticate(request);
        // Set context as authentication.
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Get user data.
        final LoginUserDetail principal = (LoginUserDetail) authentication.getPrincipal();
        return new AuthResult(principal.getUsername(), principal.getPermissionList(),
                principal.getAuthorities()
                        .stream().map(authority -> authority.getAuthority())
                        .collect(Collectors.toList()));
    }
}
