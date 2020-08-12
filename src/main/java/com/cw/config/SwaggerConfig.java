/**
 * 
 */
package com.cw.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author  xueyongfei
 * @date 2020年7月24日
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	 @Bean
	    public Docket createRestApi() {
	        return new Docket(DocumentationType.SWAGGER_2)  // 指定api类型为swagger2
	                .apiInfo(apiInfo())                 // 用于定义api文档汇总信息
	                .select()
	                .apis(RequestHandlerSelectors
	                        .basePackage("com.cw.controller"))   // 指定controller包
	                .paths(PathSelectors.any())         // 所有controller
	                .build().globalOperationParameters(headers());
	    }

	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("平台接口api")        // 文档页标题
	                .contact(new Contact("大飞",// 联系人信息
	                        "https://www.****.com",
	                        "abc@***.com"))
	                .description("专为****提供的api文档")  // 详细信息
	                .version("1.0.1")   // 文档版本号
	                .termsOfServiceUrl("https://www.****.com") // 网站地址
	                .build();
	    }
	    
	    private List<Parameter> headers(){
	    	ParameterBuilder ticketPar = new ParameterBuilder();
	    	List<Parameter> pars = new ArrayList<Parameter>();
	    	ticketPar.name("User_Session").description("user token")
	    	.modelRef(new ModelRef("string")).parameterType("header")
	    	.required(false).build(); //header中的ticket参数非必填，传空也可以
	    	 pars.add(ticketPar.build()); //根据每个方法名也知道当前方法在设置什么参数
	    	 return pars;
	    }

}
