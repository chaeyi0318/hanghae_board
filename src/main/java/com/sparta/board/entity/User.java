package com.sparta.board.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")
@Getter
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 4, max = 10)
    @Pattern(regexp = "[a-z0-9]+")
    String username;

    @Column(nullable = false)
    @Size(min = 8, max = 15)
    @Pattern(regexp = "[a-zA-Z0-9`~!@#$%^&*()-_=+]+")
    String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

//    @OneToMany(mappedBy = "user")
//    private List<Board> boardList = new ArrayList<>();

    public User(String username, String password, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
