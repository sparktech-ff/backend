package com.sparktechcode.ff.core.auth.controllers;

import com.sparktechcode.ff.core.auth.payloads.LoginResponseDto;
import com.sparktechcode.ff.core.auth.services.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Getter
@RestController
@RequestMapping("common/auth")
@RequiredArgsConstructor
@Tags(value = {@Tag(name = "Common | Auth"), @Tag(name= "Auth")})
public class CommonAuthController {

    private final AuthService authService;

    @PostMapping("login")
    public LoginResponseDto login() {
        return new LoginResponseDto(authService.generateToken());
    }
}
