package com.sparta.board.controller;

import com.sparta.board.dto.LoginRequestDto;
import com.sparta.board.dto.SignupRequestDto;
import com.sparta.board.dto.UserResponseDto;
import com.sparta.board.entity.User;
import com.sparta.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public String signupUser(@RequestBody SignupRequestDto requestDto) {
        userService.signupUser(requestDto);
        return "회원가입 성공";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.login(loginRequestDto, response);
        return "로그인 성공";
    }
}
