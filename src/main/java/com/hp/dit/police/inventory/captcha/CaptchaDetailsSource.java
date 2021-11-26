package com.hp.dit.police.inventory.captcha;

import org.springframework.security.authentication.AuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;

public class CaptchaDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, CaptchaDetails> {

    @Override
    public CaptchaDetails buildDetails(HttpServletRequest context) {
        return new CaptchaDetails(context);
    }

}