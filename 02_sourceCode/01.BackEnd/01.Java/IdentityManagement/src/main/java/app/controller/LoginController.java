package app.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import app.model.AuthResult;
import app.model.LoginInfo;
import app.service.LoginService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * LoginController
 *
 */
@RestController
@RequestMapping("login")
public class LoginController {

	@Autowired
	private LoginService loginService;
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	/**
	 * ログイン処理
	 * @param loginInfo
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.POST)
	@ApiOperation(value="Login with id/passwrod", notes="Login with user id and password. " + "<br>Return logined user infomation")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiResponses(value = { @ApiResponse(code = 401, message = "Invalid user id or password."), @ApiResponse(code = 500, message = "Internal Server Error")})
	public ResponseEntity<AuthResult> login(@RequestBody LoginInfo loginInfo, HttpServletRequest request,HttpServletResponse response){
		//認証処理を実行
		AuthResult authResult = null;
		ResponseEntity<AuthResult> res = null;
		try {
			authResult = loginService.login(loginInfo);
		} catch (AuthenticationException e) {
			//認証失敗時は401エラーを返却
			res = new ResponseEntity<AuthResult>(authResult,null,HttpStatus.UNAUTHORIZED);
			logger.error("authError", e.getMessage());
		} catch (Exception e) {
			res = new ResponseEntity<AuthResult>(authResult,null,HttpStatus.INTERNAL_SERVER_ERROR);
			logger.error("Exception", e.getMessage());
		}
		//認証OKの場合はcsrfトークンをクッキーにセット
		if(authResult != null && authResult.getUserName() != null){
			CsrfToken csrf = (CsrfToken)request.getAttribute(CsrfToken.class.getName());
			if(csrf != null){
				Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
				String token = csrf.getToken();
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				if((cookie == null || token != null && !token.equals(cookie.getValue())) && (authentication != null && authentication.isAuthenticated())){
					cookie = new Cookie("XSRF-TOKEN", token);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
			res = new ResponseEntity<AuthResult>(authResult,null,HttpStatus.CREATED);
		}
		return res;
	}

	@RequestMapping(value="card", method=RequestMethod.POST)
	@ApiOperation(value="Login with card", notes="Login with a smart card." + "<br>Return logined user infomation")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiResponses(value = { @ApiResponse(code = 401, message = "Not found smart card."), @ApiResponse(code = 500, message = "Internal Server Error")})
	public ResponseEntity<AuthResult> loginSmartCard(HttpServletRequest request,HttpServletResponse response) {
		return new ResponseEntity<AuthResult>(null, null, HttpStatus.CREATED);
	}
}
