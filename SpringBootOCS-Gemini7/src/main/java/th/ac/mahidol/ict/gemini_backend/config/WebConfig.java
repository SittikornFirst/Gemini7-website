package th.ac.mahidol.ict.gemini_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/loginPage.html");
        registry.addViewController("/login").setViewName("forward:/loginPage.html");
        registry.addViewController("/homepage").setViewName("forward:/homePage.html");
        registry.addViewController("/create").setViewName("forward:/createSciPlan.html");
    }


}