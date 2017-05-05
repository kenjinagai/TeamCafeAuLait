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
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import app.service.LoginUserDetailsService;

/**
 * Authentication setting class.
 *
 */
@Configuration
// @EnableWebMvcSecurity
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        RequestMatcher csrfRequestMatcher = new RequestMatcher() {
            // CSRF対象外URL:
            private AntPathRequestMatcher[] requestMatchers = {
                    new AntPathRequestMatcher("/index.html"),
                    new AntPathRequestMatcher("/login"), new AntPathRequestMatcher("/login/card"),
                    new AntPathRequestMatcher("/v2/api-docs"),
                    new AntPathRequestMatcher("/swagger-ui.html"),
                    new AntPathRequestMatcher("/card/id"),
                    new AntPathRequestMatcher("/webjars/springfox-swagger-ui/**"),
                    new AntPathRequestMatcher("/webjars/springfox-swagger-ui/**/**"),
                    new AntPathRequestMatcher("/swagger-resources"),
                    new AntPathRequestMatcher("/swagger-resources/configuration/**") };

            @Override
            public boolean matches(HttpServletRequest request) {
                for (AntPathRequestMatcher rm : requestMatchers) {
                    if (rm.matches(request)) {
                        return false;
                    }
                }
                return true;
            }
        };

        http.authorizeRequests()
                // Anti Match
                .antMatchers("/index.html",
                        "/login",
                        "/login/card",
                        "/card/id",
                        "/v2/api-docs",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/webjars/springfox-swagger-ui/**",
                        "/webjars/springfox-swagger-ui/**/**",
                        "/swagger-resources",
                        "/swagger-resources/configuration/**")
                .permitAll()
                .anyRequest()
                .authenticated() // 上記にマッチしなければ未認証の場合エラー
                .and()
                // ログアウト実行apiを指定
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/api/logout")).and()
                .csrf()
                .requireCsrfProtectionMatcher(csrfRequestMatcher)
                .csrfTokenRepository(this.csrfTokenRepository());
    }

    // セッションヘッダーにCSRFトークンを設定
    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }

    private Filter csrfHeaderFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request,
                    HttpServletResponse response,
                    FilterChain filterChain) throws ServletException, IOException {
                CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
                if (csrf != null) {
                    Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
                    String token = csrf.getToken();
                    if (cookie == null || token != null && !token.equals(cookie.getValue())) {
                        cookie = new Cookie("XSRF-TOKEN", token);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                    }
                }
                filterChain.doFilter(request, response);
            }
        };
    }

    // 認証処理設定
    @Configuration
    static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
        // 認証ユーザ取得サービス
        @Autowired
        private LoginUserDetailsService userDetailService;

        // ユーザパスワードをハッシュ化するEncoder設定
        // パスワードハッシュに特化したアルゴリズムBCryptを指定
        @Bean
        PasswordEncoder passwordEncoder() {
            return NoOpPasswordEncoder.getInstance();
            // return new BCryptPasswordEncoder();
        }

        // 認証処理設定
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth
                    // 認証ユーザ取得サービスを指定
                    .userDetailsService(userDetailService)
                    // パスワード照合時のEncoderを指定
                    .passwordEncoder(passwordEncoder());
        }
    }

}
