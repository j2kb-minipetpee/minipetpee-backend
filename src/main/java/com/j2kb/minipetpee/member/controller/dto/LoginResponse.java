package com.j2kb.minipetpee.member.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginMemberResponse {
    private final String accessToken;
    private final String refreshToken;
}
