package com.j2kb.minipetpee.member.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ValidateEmailResponse {
    private final String email;
}
