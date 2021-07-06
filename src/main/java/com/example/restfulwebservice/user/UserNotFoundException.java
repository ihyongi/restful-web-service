package com.example.restfulwebservice.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Http Status code
//2XX->OK
//4XX->Client
//5XX->Server
//사용자가 존재하지않을때? 500에러보단 리소스가 없어서니까 404 not found
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);

        /**
         * 예외클래스 작성방법..
         * message로 바꿨고
         * RuntimeException으로 바꿈
         * super(message)까지
         */
    }
}
