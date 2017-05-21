package app.service;

import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import app.constant.TicketConstants;
import app.model.AuthResult;
import app.model.LoginInfo;
import app.model.LoginUserDetail;

/**
 * Login Service.
 * This is implemented with Spring Security.
 *
 * @author Kenji Nagai.
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

    /**
     * Set CSRF Cookie.
     *
     * @param authResult Loggined user info.
     * @param request Request using for check CSRF cookie.
     * @param response Set a CSRF Cookie.
     */
    public void setCsrfCookie(final AuthResult authResult, final HttpServletRequest request,
            final HttpServletResponse response) {
        if ((authResult != null) && (authResult.getUserName() != null)) {
            final CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
            // If there isn't a CSRF token in request, set a CRSR token.
            if (csrf != null) {
                Cookie cookie = WebUtils.getCookie(request, TicketConstants.COOKIE_NAME_CSRF);
                final String token = csrf.getToken();
                // Get loggined user info.
                final Authentication authentication = SecurityContextHolder.getContext()
                        .getAuthentication();
                // If Cookie could be writted, do it.
                if (((cookie == null) || ((token != null) && !token.equals(cookie.getValue())))
                        && ((authentication != null) && authentication.isAuthenticated())) {
                    cookie = new Cookie(TicketConstants.COOKIE_NAME_CSRF, token);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
    }
}
