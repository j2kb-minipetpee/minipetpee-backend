package com.j2kb.minipetpee.member.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class ValidateEmailResponse {
    private String email;

    public ValidateEmailResponse(String email) {
        this.email = email;
    }
}
