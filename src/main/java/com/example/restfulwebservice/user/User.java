package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor //디폴트생성자
//@JsonIgnoreProperties(value = {"password","ssn"}) //이정보 막아주세요~
@JsonFilter("UserInfo")
public class User {
    private Integer id;

    @Size(min=2, message = "name은 2글자 이상 입력해주세요")
    private String name;
    @Past
    private Date joinDate; //회원가입날짜는 과거데이터만 사용가능하다

    private String password;
    private String ssn;//주민등록번호
}
