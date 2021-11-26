package com.hp.dit.police.inventory.captcha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CaptchaAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    @Override
    public void setUserDetailsService( @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        super.setUserDetailsService(userDetailsService);
        super.setPasswordEncoder(passwordEncoder());
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {


        Object object = authentication.getDetails();
        if(!(object instanceof CaptchaDetails)) {
            throw new InsufficientAuthenticationException("Captcha Details Not Found.");
        }

        CaptchaDetails captchaDetails = (CaptchaDetails) object;
        String captcha = captchaDetails.getCaptcha();
        if(captcha != null) {
            if(!captcha.equals(captchaDetails.getAnswer())) {
                throw new InsufficientAuthenticationException("Captcha does not match.");
            }
        }

        super.additionalAuthenticationChecks(userDetails, authentication);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}
