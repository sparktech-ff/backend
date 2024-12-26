package com.sparktechcode.ff.core.auth.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
