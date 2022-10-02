package com.hp.dit.election_ems.captcha;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class CaptchaDetails implements Serializable {

    private final String answer;
    private final String loginCaptcha;

    public CaptchaDetails(HttpServletRequest request) {
        this.answer = request.getParameter("captcha");
       // this.loginCaptcha = (String) WebUtils.getSessionAttribute(request, "loginCaptcha");
        System.out.println(request.getSession().getAttribute("CAPTCHA"));
        this.loginCaptcha = (String) request.getSession().getAttribute("CAPTCHA");
    }

    public String getAnswer() {
        return answer;
    }

    public String getCaptcha() {
        return loginCaptcha;
    }
}
