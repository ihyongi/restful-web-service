package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id @GeneratedValue
    private Integer id;

    private String description;

    //User(main):Post(sub)->1:N
    @ManyToOne(fetch = FetchType.LAZY) //지연로딩:매번 post가 호출되는것이아니라 필요할때만?
    @JsonIgnore //해당데이터는 표기되지않는다
    private User user;



}
