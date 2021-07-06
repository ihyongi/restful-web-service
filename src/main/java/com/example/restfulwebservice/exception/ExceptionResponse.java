package com.example.restfulwebservice.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    //예외처리를 위한 자바객체
    private Date timestamp; //발생시간
    private String message; //오류메시지
    private String details; //예외의 상세정보

    //이거다음에 핸들러클래스만들기 AOP(항상실행시켜줘야 하는 공동된 로직사용)
}
