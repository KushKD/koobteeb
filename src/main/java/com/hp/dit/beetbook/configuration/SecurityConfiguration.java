package com.hp.dit.beetbook.configuration;


//import com.hp.dit.beetbook.captcha.CaptchaAuthenticationProvider;
import com.hp.dit.beetbook.captcha.CaptchaDetailsSource;
import com.hp.dit.beetbook.captcha.CustomAuthenticationProvider;
import com.hp.dit.beetbook.utilities.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.WebUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  //  @Autowired
  //  private CaptchaAuthenticationProvider authenticationProvider;

    //KD New Test
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);  //authenticationProvider
    }
    @Autowired
    private CaptchaDetailsSource detailsSource;

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);




    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

     //   http.addFilterBefore(new SameSiteFilter(), UsernamePasswordAuthenticationFilter.class);
        //Working Earlier
        http.headers().addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN));
        http.csrf() .csrfTokenRepository(csrfTokenRepository()) .and().addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);

        http.csrf().ignoringAntMatchers("/nocsrf", "/api/**");
        http.csrf().ignoringAntMatchers("/nocsrf", "/apidataTable/**");
        http.csrf().ignoringAntMatchers("/nocsrf", "/ajax/**");
        http.headers().frameOptions().sameOrigin();

//        http.headers()
//                .xssProtection()
//                .and()
//                .contentSecurityPolicy("script-src 'self'");



        http.authorizeRequests()
                .antMatchers("/resources/**").denyAll()
                .antMatchers(Constants.permitAll).permitAll()
                .antMatchers(Constants.permitApiDatatable).permitAll()
                .antMatchers("/downloadFile/**").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/index").hasAnyRole("ADMIN","SUPER ADMIN")
                .antMatchers("/createuser/**").hasAnyRole("ADMIN")
                .antMatchers("/saveuser/").hasAnyRole("ADMIN")
                .antMatchers("/createrole/").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin( )
                .loginPage(Constants.loginController)
                //.loginProcessingUrl("/LoginProcessing")
                .successHandler(loginSuccessHandler())
                .failureHandler(loginFailureHandler())
                .authenticationDetailsSource(detailsSource)
                .permitAll().and()
                .logout().logoutSuccessHandler(logoutSuccessHandler())
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies(Constants.JSESSIONID,Constants.headerNameXXSRF)
                .and()
                .sessionManagement()
                .maximumSessions(1)
                .and()
                .invalidSessionUrl(Constants.loginController);



    }

    private Filter csrfHeaderFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

                CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
                if (csrf != null) {
                    Cookie cookie = WebUtils.getCookie(request, Constants.headerNameXSRF);
                    String token = csrf.getToken();
                    if (cookie == null || token != null && !token.equals(cookie.getValue())) {
                        cookie = new Cookie(Constants.headerNameXSRF, token);
                        cookie.setPath(Constants.seperator);
                        cookie.setSecure(true);
                        cookie.setHttpOnly(true);
                        response.addCookie(cookie);
                    }
                }

                Collection<String> headers = response.getHeaders(Constants.setHeaderCokkie);
                boolean firstHeader = true;
                for (String header : headers) {
                    if (firstHeader) {
                        response.setHeader(Constants.setHeaderCokkie, String.format(Constants.formatting, header, Constants.SameSite_Strict));
                        firstHeader = false;
                        continue;
                    }
                    response.addHeader(Constants.setHeaderCokkie, String.format(Constants.formatting, header, Constants.SameSite_Strict));
                }
                request.setCharacterEncoding(Constants.utf8);
                response.setContentType(Constants.text_html);
                response.setHeader(Constants.pragma, Constants.no_cache);
                response.setHeader(Constants.Cache_control, Constants.nocache_nostore_mustrevalidate);
                filterChain.doFilter(request, response);
            }
        };
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName(Constants.headerNameXXSRF);


        return repository;
    }


    private AuthenticationSuccessHandler loginSuccessHandler() {
        return (request, response, authentication) -> response
                .sendRedirect(ServletUriComponentsBuilder.fromCurrentContextPath().path(Constants.indexController).toUriString());
    }

    private AuthenticationFailureHandler loginFailureHandler() {
        return (request, response, exception) -> {
            request.getSession().setAttribute(Constants.errorsec, Constants.secError);
            response.sendRedirect(ServletUriComponentsBuilder.fromCurrentContextPath().path(Constants.loginController).toUriString());
        };
    }

    private LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, authentication) -> {
            request.getSession().setAttribute(Constants.messagesec, Constants.secMessage);
            response.sendRedirect(ServletUriComponentsBuilder.fromCurrentContextPath().path(Constants.loginController).toUriString());
        };
    }




}
