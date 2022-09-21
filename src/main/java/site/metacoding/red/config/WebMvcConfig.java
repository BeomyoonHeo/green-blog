package site.metacoding.red.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import site.metacoding.red.handler.HelloInterceptor;
import site.metacoding.red.handler.LoginIntercepter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginIntercepter())
		.addPathPatterns("/s/**");
		
		registry.addInterceptor(new HelloInterceptor()).addPathPatterns("/hello/**");
		
//		.addPathPatterns("/admin/**") // 여러개의 pattern을 등록 가능
//		.excludePathPatterns("/s/boards/**"); //제외 할 수 있다. 
//		// /s/* => /s/boards, s/users     XXXX /s/boards/1
	}
}
