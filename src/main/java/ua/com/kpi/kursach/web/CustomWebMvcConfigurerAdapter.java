package ua.com.kpi.kursach.web;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ua.com.kpi.kursach.util.FileUploader;

@Component
public class CustomWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
    public static final String UPLOADED_RESOURCE_PATTERNS_NAME = "uploaded";
    
    
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/" + UPLOADED_RESOURCE_PATTERNS_NAME + "/**").addResourceLocations(
                "file:///" + FileUploader.UPLOAD_FOLDER + "/");
    }
}
