package com.sparta.board.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignupRequestDto {
    @Size(min = 4, max = 10)
    @Pattern(regexp = "[a-z0-9]+")
    private String username;

    @Size(min = 8, max = 15)
    @Pattern(regexp = "[a-zA-Z0-9`~!@#$%^&*()-_=+]+")
    private String password;

    private boolean admin = false;

    private String adminToken = "";
}
