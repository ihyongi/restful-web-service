package com.example.restfulwebservice.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUserController {

    private UserDaoService service; //빈 주입

    //의존성주입
    //1.생성자의 매개변수를 통해서 (V)
    public AdminUserController(UserDaoService service){
        this.service=service;
    }
    //2.@autowired -->나는 이렇게 사용해왔다..

    //사용자 전체목록조회
    @GetMapping("/users")
    public MappingJacksonValue retrieveAllusers(){
        List<User> users = service.findAll();

        //포함시키고자하는 필터설정
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id","name","joinDate","password");
        //이 필터를 사용할 수 있게 변경이 필요
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",filter); //셋팅해놓은거

        //얘를 리턴하려면 리턴타입이 User가 아니라 MappingJacksonValue로 바꿔줌
        MappingJacksonValue mapping =new MappingJacksonValue(users); //user데이터값 넣기
        //필터를 적용시키려면?
        mapping.setFilters(filters);

        return mapping;
    }

    //사용자 개별조회
    //Get /admin/users/1 -> 버전을 포함시켜 /admin/v1/users/1로 바꿔보자
    @GetMapping("/v1/users/{id}")
    public MappingJacksonValue retrieveUserV1(@PathVariable int id){
        User user = service.findOne(id);
        if (user==null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        //포함시키고자하는 필터설정
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id","name","joinDate","password","ssn");
        //이 필터를 사용할 수 있게 변경이 필요
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",filter); //셋팅해놓은거

        //얘를 리턴하려면 리턴타입이 User가 아니라 MappingJacksonValue로 바꿔줌
        MappingJacksonValue mapping =new MappingJacksonValue(user); //user데이터값 넣기
        //필터를 적용시키려면?
        mapping.setFilters(filters);

        return mapping; //반환값은 매핑
    }

    @GetMapping("/v2/users/{id}")
    public MappingJacksonValue retrieveUserV2(@PathVariable int id){
        User user = service.findOne(id);
        if (user==null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        //User ->User2로 복사
        UserV2 userV2 =new UserV2();
        BeanUtils.copyProperties(user, userV2); //빈들간의 관련작업을 도와주는 클래스
        userV2.setGrade("VIP");

        //포함시키고자하는 필터설정
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id","name","joinDate","grade");
        //이 필터를 사용할 수 있게 변경이 필요
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", filter); //셋팅해놓은거

        //얘를 리턴하려면 리턴타입이 User가 아니라 MappingJacksonValue로 바꿔줌
        MappingJacksonValue mapping =new MappingJacksonValue(user); //user데이터값 넣기
        //필터를 적용시키려면?
        mapping.setFilters(filters);

        return mapping; //반환값은 매핑
    }


}
