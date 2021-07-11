package com.example.restfulwebservice.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/jpa")
public class UserJpaController {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PostRepository postRepository;


    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        List<User> users = userRepository.findAll(); //전체사용자 목록
        return users;
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id); //존재하거나 존재하지 않을 수 있어서 Optional로 반환

        if(!user.isPresent()){
            throw new UserNotFoundException(String.format("ID{%s} not found",id));
        }

        //헤테오스쓸때 resource대신에 이거쓴다다
        EntityModel<User> model=new EntityModel<>(user.get());
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        model.add(linkTo.withRel("all-users"));

        return model;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
    }

    //새로운 사용자 추가
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);

        //아이디값 자동지정
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    //postEntity사용가능한 메서드
    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveAllPostByUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id); //존재하거나 존재하지 않을 수 있어서 Optional로 반환

        if(!user.isPresent()){
            throw new UserNotFoundException(String.format("ID{%s} not found",id));
        }
        return user.get().getPosts();

    }

    //새로운 포스팅
    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> createPost(@PathVariable int id, @RequestBody Post post){
        Optional<User> user = userRepository.findById(id); //존재하거나 존재하지 않을 수 있어서 Optional로 반환
        if(!user.isPresent()){
            throw new UserNotFoundException(String.format("ID{%s} not found",id));
        }
        post.setUser(user.get()); //user객체생성--사용자정보지정
        Post savedPost = postRepository.save(post);
        //아이디값 자동지정
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
