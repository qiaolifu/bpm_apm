package bpm.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2

public class SwaggerConfig {
    //  访问地址增加如下地址/swagger-ui.html
    @Bean
    public Docket createRestApi() {
	 	ParameterBuilder tokenPar = new ParameterBuilder();  
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("token").description("令牌")
        .modelRef(new ModelRef("string")).parameterType("query").required(false).build();
        pars.add(tokenPar.build());  
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
               // .apis(RequestHandlerSelectors.basePackage("com.sst"))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))//错误路径不监控
                .paths(PathSelectors.regex("/.*"))// 对根下所有路径进行监控
                .build().globalOperationParameters(pars)  ;
    }

	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("ERP系统")
                .description("开放api接口")
                .termsOfServiceUrl("http://www.daiyilu.com")
                .contact("廖亮 06125065@163.com")
                .version("1.0")
                .build();
    }
 
}