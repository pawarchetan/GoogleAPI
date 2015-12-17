package com.loginext.configuration;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

@Log4j2
public class ApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        log.info("Inside ApplicationInitializer............");
        log.info("Configuring the Web Application Context..........");
        Dynamic servlet;
        try (AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext()) {
            ctx.register(SpringConfigurator.class);
            ctx.setServletContext(servletContext);
            servlet = servletContext.addServlet("rest-dispatcher", new DispatcherServlet(ctx));
        }
        servlet.addMapping("/loginext/*");
        servlet.setLoadOnStartup(1);
    }
}
