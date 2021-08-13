package com.j2kb.minipetpee.member.controller.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SaveMemberRequest {

    private final String email;
    private final String password;
    private final String name;
}
