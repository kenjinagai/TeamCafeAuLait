package app.service;

import java.util.List;
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

import app.constant.Constants;
import app.entity.ExtendedAuthentication;
import app.entity.User;
import app.model.AuthResult;
import app.model.LoginInfo;
import app.model.LoginUserDetail;
import app.repository.ExtendedAuthenticationRepository;
import app.repository.UserRepository;

/**
 * Login Service.
 * This is implemented with Spring Security.
 *
 * @author Kenji Nagai.
 *
 */
@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authMgr;

    @Autowired
    private ExtendedAuthenticationRepository extAuthrepo;

    @Autowired
    private UserRepository userRepo;

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
     * Extended Authentication.
     * e.g. IC Card.
     *
     * @param extendedAuthentication ExtendedAuthentication which has identification data.
     * @return User Authentication Parameter
     * @throws IllegalArgumentException invalid parameter or Extended Authentication doesn't exist.
     * @throws AuthenticationException authentication failure
     * @throws Exception internal error
     */
    public AuthResult extendedAuthentication(final ExtendedAuthentication extendedAuthentication)
            throws IllegalArgumentException, AuthenticationException, Exception {
        if (extendedAuthentication.getExtendedAuthenticationValue() == null) {
            throw new IllegalArgumentException();
        }
        final ExtendedAuthentication extendeAuth = this.extAuthrepo
                .findByExtendedAuthenticationValueStartingWith(
                        extendedAuthentication.getExtendedAuthenticationValue());

        final User user = this.userRepo.findOne(extendeAuth.getUserId());

        final LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUserId(user.getUserId());
        loginInfo.setPassword(user.getEncodedPassword());
        return this.login(loginInfo);
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
                Cookie cookie = WebUtils.getCookie(request, Constants.COOKIE_NAME_CSRF);
                final String token = csrf.getToken();
                // Get loggined user info.
                final Authentication authentication = SecurityContextHolder.getContext()
                        .getAuthentication();
                // If Cookie could be writted, do it.
                if (((cookie == null) || ((token != null) && !token.equals(cookie.getValue())))
                        && ((authentication != null) && authentication.isAuthenticated())) {
                    cookie = new Cookie(Constants.COOKIE_NAME_CSRF, token);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
    }

    /**
     * Whether user is admin.
     *
     * @param request
     * @return Whether user is admin.
     */
    public boolean isAdmin(final HttpServletRequest request) {
        return request.isUserInRole(Constants.ROLE_ADMIN);
    }

    /**
     * Get loggined user name.
     *
     * @param request REST API request.
     * @return loggined user name.
     */
    public String getLoginnedUsername(final HttpServletRequest request) {
        return request.getUserPrincipal().getName();
    }

    /**
     * Get loggined user permission list.
     * @param request REST API request.
     * @return loggined user permission list.
     */
    public List<String> getLogginedUserPermissionList(final HttpServletRequest request) {
        return this.getLogginedUser(request).getPermissionList();
    }

    /**
     * Get loggined user info.
     *
     * @param request REST API request.
     * @return loggined user info.
     */
    private LoginUserDetail getLogginedUser(final HttpServletRequest request) {
        final UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) request
                .getUserPrincipal();
        return (LoginUserDetail) token.getPrincipal();
    }
}
