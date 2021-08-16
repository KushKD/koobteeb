package com.hp.dit.beetbook;

import com.hp.dit.beetbook.captcha.CaptchaDetailsSource;
import com.hp.dit.beetbook.captcha.CaptchaGenServlet;
import com.hp.dit.beetbook.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import java.util.Collections;

@SpringBootApplication
@EnableCaching
@EnableScheduling
@EnableConfigurationProperties({FileStorageProperties.class})
public class BeetbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeetbookApplication.class, args);
	}


	@Bean
	ServletRegistrationBean captchaServletRegistration () {
		ServletRegistrationBean srb = new ServletRegistrationBean();
		srb.setName("CaptchaServlet");
		srb.setServlet(new CaptchaGenServlet());
		srb.addUrlMappings("/captcha.jpg");
		return srb;
	}

	@Bean
	public ServletContextInitializer servletContextInitializer() {
		return new ServletContextInitializer() {
			@Override
			public void onStartup(ServletContext servletContext) throws ServletException {
				servletContext.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE));
				SessionCookieConfig sessionCookieConfig = servletContext.getSessionCookieConfig();
				sessionCookieConfig.setHttpOnly(true);
				sessionCookieConfig.setSecure(true);
			}
		};
	}


	@Bean
	public CaptchaDetailsSource getCaptchaDetailsSource() {
		return new CaptchaDetailsSource();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}



}
