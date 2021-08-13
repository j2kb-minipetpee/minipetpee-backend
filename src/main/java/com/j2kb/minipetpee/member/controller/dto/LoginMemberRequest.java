package com.j2kb.minipetpee.member.controller.dto;

import lombok.Data;

@Data
public class LoginMemberRequest {
    private String email;
    private String password;

    public LoginMemberRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
