package com.edmunds.config;

import org.h2.server.web.WebServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration  {
    private static Logger logger = LoggerFactory.getLogger(WebConfiguration.class);
    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        logger.info("Overriding default h2 console mapping.");
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/console/*");
        return registration;
    }

}
