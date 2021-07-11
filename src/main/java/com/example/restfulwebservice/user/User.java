package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor //디폴트생성자
//@JsonIgnoreProperties(value = {"password","ssn"}) //이정보 막아주세요~
//@JsonFilter("UserInfo")
@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체") //1.swagger
@Entity
public class User {
    @Id @GeneratedValue
    private Integer id;

    @Size(min=2, message = "name은 2글자 이상 입력해주세요")
    @ApiModelProperty(notes = "사용자 이름을 입력해주세요")
    private String name;

    @Past
    @ApiModelProperty(notes = "사용자의 등록일을 입력해주세요")
    private Date joinDate; //회원가입날짜는 과거데이터만 사용가능하다

    @ApiModelProperty(notes = "사용자의 패스워드를 입력해주세요")
    private String password;

    @ApiModelProperty(notes = "사용자의 주민번호를를 입력주세요")
    private String ssn;//주민등록번호

    @OneToMany(mappedBy = "user") //여기가 주
    private List<Post> posts;


    public User(int id, String name, Date joinDate, String password, String ssn) {
        this.id=id;
        this.name=name;
        this.joinDate=joinDate;
        this.password=password;
        this.ssn=ssn;
    }
}
