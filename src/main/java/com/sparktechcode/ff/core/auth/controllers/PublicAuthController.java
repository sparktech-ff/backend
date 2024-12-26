package com.sparktechcode.ff.core.auth.controllers;

import com.sparktechcode.ff.core.auth.payloads.LoginRequestDto;
import com.sparktechcode.ff.core.auth.payloads.LoginResponseDto;
import com.sparktechcode.ff.core.auth.services.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Getter
@RestController
@RequestMapping("public/auth")
@RequiredArgsConstructor
@Tags(value = {@Tag(name = "Public | Auth"), @Tag(name= "Auth")})
public class PublicAuthController {

    private final AuthService authService;

    @PostMapping("login")
    public LoginResponseDto login(@RequestBody @Valid LoginRequestDto request) {
        return new LoginResponseDto(authService.generateToken(request));
    }
}
