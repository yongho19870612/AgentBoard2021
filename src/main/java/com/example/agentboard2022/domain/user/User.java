package com.example.agentboard2022.domain.user;

import com.example.agentboard2022.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    //사용자의 권한을 관리할 Enum 클래스 Role 생성
    // JPA로 데이터베이스로 저장할 때 Enum 값을 어떤 형태로 저장할지를 결정
    // 기본적으로는 Int로 된 숫자가 저장되나 이를 문자열(EnumType.String)로 저장될 수 있도록 선언
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role){
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture){
        this.name = name;
        this.picture = picture;
        return this;
    }

    public String getRoleKey(){

        return this.role.getKey();
    }

}
