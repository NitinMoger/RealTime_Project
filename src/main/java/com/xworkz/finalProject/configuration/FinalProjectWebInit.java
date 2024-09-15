package com.xworkz.finalProject.configuration;

import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class FinalProjectWebInit extends AbstractAnnotationConfigDispatcherServletInitializer implements WebMvcConfigurer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ProjectConfiguration.class, DatabaseConfiguration.class, MailConfiguration.class, FileUploadConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        System.out.println("Running configureDefaultServletHandling method in FinalProject Webinit");
        configurer.enable();
    }
}
