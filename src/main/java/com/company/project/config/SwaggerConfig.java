package com.company.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author tangmf
 * @Date 2020/4/21 16:32
 * @Description swaager配置类
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.company.project")).build().apiInfo(testApiInfo());
    }

    private ApiInfo testApiInfo() {
        return new ApiInfo("springboot-collection-seed", // 大标题
                "springboot-collection-seed", // 小标题
                "v1.0", // 版本
                "NO terms of server", "tangmf", // 作者
                "The Apache License, Version 2.0", // 链接显示文字
                "http://www.apache.org/licenses/LICENSE-2.0.html"// 网站链接
        );
    }
}
