package com.hp.dit.beetbook.captcha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {


    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

        @Override
        public Authentication authenticate(Authentication authentication)
      throws AuthenticationException {


            byte[] decodedU = Base64.getDecoder().decode(authentication.getName());
            String decodedUsername = new String(decodedU, StandardCharsets.UTF_8);
            System.out.println(decodedUsername);

            byte[] decodedP = Base64.getDecoder().decode(authentication.getCredentials().toString());
            String decodedPassword = new String(decodedP, StandardCharsets.UTF_8);
            System.out.println(decodedPassword);

            Object object = authentication.getDetails();
            if(!(object instanceof CaptchaDetails)) {
                throw new InsufficientAuthenticationException("Captcha Details Not Found.");
            }

            CaptchaDetails captchaDetails = (CaptchaDetails) object;
            String captcha = captchaDetails.getAnswer();
            byte[] decodedC = Base64.getDecoder().decode(captcha);
            String decodedCaptcha = new String(decodedC, StandardCharsets.UTF_8);
            System.out.println(decodedCaptcha);

            if(decodedCaptcha != null) {
                if(!decodedCaptcha.equals(captchaDetails.getCaptcha())) {
                    throw new InsufficientAuthenticationException("Captcha does not match.");
                }
            }

            UserDetails userDetails =  userDetailsService.loadUserByUsername(decodedUsername);

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,
                    userDetails.getPassword(), userDetails.getAuthorities());

            //return (Authentication) userDetailsService.loadUserByUsername(decodedUsername);
//            if (shouldAuthenticateAgainstThirdPartySystem()) {
//
//                // use the credentials
//                // and authenticate against the third-party system
                return token;
//            } else {
//                return null;
//            }
        }

        @Override
        public boolean supports(Class<?> authentication) {
            return authentication.equals(UsernamePasswordAuthenticationToken.class);
        }
}
