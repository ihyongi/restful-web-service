package com.example.restfulwebservice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
//Enable Swagger
public class SwaggerConfig {

    //연락처정보
    private static final Contact DEFAULT_CONTACT =new Contact("Kenneth Lee","http://www.joneconsulting.co.kr","025031210@naver.com");

    //API info--상수 고정시 변경이 필요없어서 만듦?
    //제목, description, version, 서비스이름,위에서 생성한 객체, 라이센스정보
    private static final ApiInfo DEFAULT_API_INFO =new ApiInfo("Awesome API Title",
            "My User management REST API service", "1.0", "urn:tos", DEFAULT_CONTACT, "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());

    //문서타입지정--asList()는 배열형태의 값을 리스트형태로 바꿔줌
    //"application/json","application/xml" 두가지 타입을 지원합니다를 표기
    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =new HashSet<>(Arrays.asList("application/json","application/xml"));

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }

    //Swagger2
    //ALI the paths
    //all the apis
}
