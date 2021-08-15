package com.j2kb.minipetpee.member.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaveMemberRequest {
    private String email;
    private String password;
    private String name;
}
