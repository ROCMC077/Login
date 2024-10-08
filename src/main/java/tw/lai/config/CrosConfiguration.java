package tw.lai.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrosConfiguration implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// 映射路徑
		registry.addMapping("/**")
				// 允許跨網域請求的來源
				.allowedOriginPatterns("*")
				// 允許跨域攜帶cookie資訊，預設跨網域請求是不攜帶cookie資訊的。
				.allowCredentials(true)
				// 允許使用那些請求方式
				.allowedMethods("GET", "POST", "PUT", "DELETE")
				.maxAge(3600)
				// 允許哪些Header
				.allowedHeaders("*");
				
	}
	
	/*
     * 跨域配置后swagger2可能不能访问，需要增加如下配置
     * 原因: 不能访问的原因的swagger的内置接口被拦截器拦下来了，需要将swagger加到拦截器的排除列表中。
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


}
