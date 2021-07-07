package com.example.restfulwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class RestfulWebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfulWebServiceApplication.class, args);
    }

    //빈등록하기 --초기화시 빈에 등록된 정보가 메모리에 올라감
    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.KOREA); //한국어처리가 기본
        return localeResolver;
    }
}
