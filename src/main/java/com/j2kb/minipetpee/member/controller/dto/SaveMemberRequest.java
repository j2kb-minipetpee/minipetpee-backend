package com.j2kb.minipetpee.member.controller.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
public class SaveMemberRequest {

    private String email;
    private String password;
    private String name;

    public SaveMemberRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
