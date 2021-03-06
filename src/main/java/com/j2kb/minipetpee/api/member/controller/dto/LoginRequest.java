package com.j2kb.minipetpee.api.member.controller.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginRequest {
    @NotNull(message = "EMP1006")
    @Email(regexp = "[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]$", message = "EMP1011")
    private String email;

    @NotNull(message = "EMP1008")
    @Size(min = 8, message = "EMP1012")
    private String password;
}
