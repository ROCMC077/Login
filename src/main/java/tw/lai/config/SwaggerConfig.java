package tw.lai.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 // 開啟Swagger2
@EnableWebMvc
public class SwaggerConfig {

	// 配置Swagger的Docket的bean
	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				//驗證JWT token
				.securityContexts(Arrays.asList(securityContext()))
			    .securitySchemes(Arrays.asList(apiKey()))   
				//enable設定是否啟動swagger
				//.enable(false)
				.select()
				//RequestHandlerSelectors,配置要掃描接口方式
				//basePackage資料夾
				//.apis(RequestHandlerSelectors.any())掃描全部
				//.apis(RequestHandlerSelectors.none())都不掃描
				.apis(RequestHandlerSelectors.basePackage("tw.lai.controller"))
				//.paths()過濾路徑
				//只過濾路徑帶有lai的接口
				//.paths(PathSelectors.ant("/lai/**"))
				.build();
	}
	
	//如果想要多個分組:多幾個Docket就好
	//範例 把分組a包含lai路徑都過濾掉
	@Bean
	public Docket docket1() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("a")
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("tw.lai.controller"))
				//.paths()過濾路徑
				//只過濾路徑帶有lai的接口
				.paths(PathSelectors.ant("/lai/**"))
				.build();
				
	}
	
	
	
	//配置Swagger訊息==.apiInfo
	public ApiInfo apiInfo() {
		springfox.documentation.service.Contact contact = new springfox.documentation.service.Contact
													   ("Lai",
														"https://www.google.com.tw/?hl=zh_TW",
														 "onlyforwork0404@gmail.com");
		return new  ApiInfo(
				"新人訓練",
				"描述訊息",
				"v1.0",
				"https://tw.yahoo.com/",
				contact,
				"Apache 2.0",
				"http://www.apache.org/licenses/LICENSE-2.0",
				new ArrayList<VendorExtension>());
				
		
	}
	
	
	private ApiKey apiKey() { 
	    return new ApiKey("JWT", "Authorization", "header"); 
	}
	
	private SecurityContext securityContext() { 
	    return SecurityContext.builder().securityReferences(defaultAuth()).build(); 
	} 

	private List<SecurityReference> defaultAuth() { 
	    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
	    authorizationScopes[0] = authorizationScope; 
	    return Arrays.asList(new SecurityReference("JWT", authorizationScopes)); 
	}
}
