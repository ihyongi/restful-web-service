package com.example.restfulwebservice.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private UserDaoService service; //빈 주입

    //의존성주입
    //1.생성자의 매개변수를 통해서 (V)
    public UserController(UserDaoService service){
        this.service=service;
    }
    //2.@autowired -->나는 이렇게 사용해왔다..

    //사용자 전체목록조회
    @GetMapping("/users")
    public List<User> retrieveAllusers(){
        return service.findAll();
    }

    //사용자 개별조회 (문자형으로 받아온다)
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){
        User user = service.findOne(id);
        //데이터베이스에 존재하지않은 데이터를 불러와도 200이뜬다 ->존재하지않는 데이터의경우에 예외발생시켜보자
        if (user==null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        return user;
    }

    //사용자 추가
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        //json방식으로 값이 오고 유효성검사를 하게됨
        User savedUser = service.save(user);

        //사용자에게 요청값을 변환해주기위해서, 현재 가지고있는 request값, 반환시의 path값
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        //지금만들어진 uri값을 반환시키려면?
        return ResponseEntity.created(location).build(); //uri저장된 값으로 빌드해서 반환
    }

    //사용자 삭제
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
       User user=service.deleteById(id);
       if (user ==null){
           throw new UserNotFoundException(String.format("ID[%s] not found", id));
       }
    }

    /*//사용자 수정 해봐야하는디용 ㅋㅋ
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable int id){
        User user=service.findOne(id); //찾아서
        user.setName("ihyongi");
        user.setJoinDate(new Date());
        service.updateOne(user);
    }*/

    @PutMapping("/users/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody User user, @PathVariable int id) {
        User findUser = service.findOne(id);
        if (findUser.getId()==null)
            return ResponseEntity.notFound().build();
        user.setId(id);
        service.save(user);
        return ResponseEntity.noContent().build();
    }
}
