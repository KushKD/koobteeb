package com.hp.dit.beetbook;

import com.hp.dit.beetbook.captcha.CaptchaDetailsSource;
import com.hp.dit.beetbook.captcha.CaptchaGenServlet;
import com.hp.dit.beetbook.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

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
	public CaptchaDetailsSource getCaptchaDetailsSource() {
		return new CaptchaDetailsSource();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}



}
