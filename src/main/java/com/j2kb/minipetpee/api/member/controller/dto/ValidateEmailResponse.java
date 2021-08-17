package com.j2kb.minipetpee.api.member.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ValidateEmailResponse {
    private final String email;
}
