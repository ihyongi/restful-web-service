package com.example.restfulwebservice.user;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserDaoService {
    //도메인 정보를 이용하여 사용자전체목록조회, 사용자 정보추가, 사용자정보 상세보기같은 비즈니스로직 여기서 처리
    private static List<User> users=new ArrayList<>();

    private static int usersCount=3; //static에 유저 3명넣고시작이니까 이다음서부터 저장되려면 4부터 저장되어야 함

    static {
        users.add(new User(1,"Kenneth", new Date(), "pass1", "701010-1111111")); //디비연동안하고 여기서는 메모리로만
        users.add(new User(2,"Alice", new Date(), "pass2", "801010-2222222"));
        users.add(new User(3,"Elena", new Date(), "pass3", "901010-3333333"));
    }

    //사용자 전체목록 조회
    public List<User> findAll(){
        return users;
    }

    //사용자 추가
    public User save(User user){
        if(user.getId()==null){
            user.setId(++usersCount); //4부터 저장
        }
        users.add(user); //추가하고
        return user; //user로 반환
    }

    //사용자의 개별데이터 조회
    public User findOne(int id){
        for (User user : users) {
            if(user.getId()==id){
                return user;
            }
        }
        //없으면?null
        return null;
    }

    //사용자 삭제
    public User deleteById(int id){
        //데이터가 존재하는가
        Iterator<User> iterator = users.iterator(); //열거형 데이터타입 iterator

        while (iterator.hasNext()){
            User user=iterator.next();

            if(user.getId()==id){
                iterator.remove();
                return user; //어떤데이터가 지워졌다~~~
            }
        }
        return null;
    }


}
