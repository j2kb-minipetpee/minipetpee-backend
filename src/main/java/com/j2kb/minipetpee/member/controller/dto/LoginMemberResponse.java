package com.j2kb.minipetpee.member.controller.dto;

import lombok.Data;

@Data
public class LoginMemberResponse {
    private String accessToken;
    private String refreshToken;

    public LoginMemberResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
