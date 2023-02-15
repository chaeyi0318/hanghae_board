package com.sparta.board.dto;

import com.sparta.board.entity.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private String username;
    private String password;

    public UserResponseDto(Users users) {
        this.username = users.getUsername();
        this.password = users.getPassword();
    }
}
