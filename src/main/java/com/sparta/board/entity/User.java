package com.sparta.board.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import javax.persistence.*;

@Entity(name = "users")
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 4, max = 10)
    @Pattern(regexp = "[a-z0-9]")
    String username;

    @Column(nullable = false)
    @Size(min = 8, max = 15)
    @Pattern(regexp = "[A-Za-z0-9]")
    String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
