package app.config;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import app.service.LoginUserDetailsService;

/**
 * Authentication setting class.
 *
 * @author Kenji Nagai.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        final RequestMatcher csrfRequestMatcher = new RequestMatcher() {
            // CSRF対象外URL:
            private final AntPathRequestMatcher[] requestMatchers = {
                    new AntPathRequestMatcher("/index.html"),
                    new AntPathRequestMatcher("/login"),
                    new AntPathRequestMatcher("/login/card"),
                    new AntPathRequestMatcher("/v2/api-docs"),
                    new AntPathRequestMatcher("/swagger-ui.html"),
                    new AntPathRequestMatcher("/card/id"),
                    new AntPathRequestMatcher("/webjars/springfox-swagger-ui/**"),
                    new AntPathRequestMatcher("/webjars/springfox-swagger-ui/**/**"),
                    new AntPathRequestMatcher("/swagger-resources"),
                    new AntPathRequestMatcher("/swagger-resources/configuration/**") };

            @Override
            public boolean matches(final HttpServletRequest request) {
                for (final AntPathRequestMatcher rm : requestMatchers) {
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
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).and()
                .csrf()
                .requireCsrfProtectionMatcher(csrfRequestMatcher)
                .csrfTokenRepository(this.csrfTokenRepository());
    }

    // セッションヘッダーにCSRFトークンを設定
    private CsrfTokenRepository csrfTokenRepository() {
        final HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
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
        @Override
        public void init(final AuthenticationManagerBuilder auth) throws Exception {
            auth
                    // 認証ユーザ取得サービスを指定
                    .userDetailsService(userDetailService)
                    // パスワード照合時のEncoderを指定
                    .passwordEncoder(passwordEncoder());
        }
    }
}
