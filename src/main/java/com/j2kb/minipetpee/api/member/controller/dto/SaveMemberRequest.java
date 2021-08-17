package com.j2kb.minipetpee.api.member.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaveMemberRequest {
    private String email;
    private String password;
    private String name;
}
