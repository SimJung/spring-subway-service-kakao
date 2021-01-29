package subway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import subway.auth.ui.LoginInterceptor;

@Configuration
public class SubwayConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/favorites/**");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/members/me");
    }
}
