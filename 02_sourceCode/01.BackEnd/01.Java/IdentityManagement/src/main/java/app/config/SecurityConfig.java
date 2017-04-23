package app.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import app.service.LoginUserDetailsService;

/**
 * ログイン、認証処理の設定クラス
 *
 */
@Configuration
//@EnableWebMvcSecurity
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			// Anti Match
			.antMatchers(
					"/index",
      				"/api/login"
      		).permitAll()
      		// Only Admin role can access.
      		.antMatchers("/admin","/api/v1/users").hasRole("ADMIN")
      		//上記パス意外へのアクセスは全て認証が必要
      		.anyRequest().authenticated()
      	//ログアウト設定
      	.and().logout()
      		//ログアウト実行apiを指定
      		.logoutRequestMatcher(new AntPathRequestMatcher("/api/logout"))
      	//CSRF対策
      	.and().csrf().csrfTokenRepository(csrfTokenRepository())
      	.and().addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);
    }

	//セッションヘッダーにCSRFトークンを設定
	private CsrfTokenRepository csrfTokenRepository(){
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}

	private Filter csrfHeaderFilter() {
		return new OncePerRequestFilter() {
			@Override
			protected void doFilterInternal(HttpServletRequest request,
					HttpServletResponse response, FilterChain filterChain)
					throws ServletException, IOException {
				CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class
						.getName());
				if (csrf != null) {
					Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
					String token = csrf.getToken();
					if (cookie == null || token != null
							&& !token.equals(cookie.getValue())) {
						cookie = new Cookie("XSRF-TOKEN", token);
						cookie.setPath("/");
						response.addCookie(cookie);
					}
				}
				filterChain.doFilter(request, response);
			}
		};
	}

	//認証処理設定
	@Configuration
	static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
		//認証ユーザ取得サービス
		@Autowired
		private LoginUserDetailsService userDetailService;

		//ユーザパスワードをハッシュ化するEncoder設定
		//パスワードハッシュに特化したアルゴリズムBCryptを指定
		@Bean
		PasswordEncoder passwordEncoder(){
			return new BCryptPasswordEncoder();
		}

		//認証処理設定
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			auth
				//認証ユーザ取得サービスを指定
				.userDetailsService(userDetailService)
				//パスワード照合時のEncoderを指定
				.passwordEncoder(passwordEncoder());
		}
	}

}
