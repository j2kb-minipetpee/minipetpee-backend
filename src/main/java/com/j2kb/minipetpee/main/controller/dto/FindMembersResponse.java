package com.j2kb.minipetpee.main.controller.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class FindMembersResponse {

    @JsonValue
    private final List<FindMemberResponse> members;
}
