package com.sparta.bbs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // nullable: null 허용 여부
    // unique: 중복 허용 여부 (false 일때 중복 허용)
    @Size(min = 4,max = 10,message ="아이디는 4에서 10자 사이 입니다.")
    @Pattern(regexp = "[a-z0-9]*$",message = "아이디 형식이 일치하지 않습니다.")
    @Column(nullable = false, unique = true)
    private String username;

    @Size(min = 8,max = 15,message ="비밀번호는 8에서 15자 사이 입니다.")
    @Pattern(regexp = "[a-zA-Z0-9`~!@#$%^&*()_=+|{};:,.<>/?]*$",message = "비밀번호 형식이 일치하지 않습니다.")
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    //Enum 의 선언된 상수의 이름을 String 클래스 타입으로 변환하여 DB에 넣는다.(즉, DB 클래스 타입은 String 이다.) --> UserRoleEnum 에서 열거혐(enum) 으로 필드가 선언되어있음
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public Member(String username, String password, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
