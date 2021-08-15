package com.j2kb.minipetpee.main.controller.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import java.util.List;

@Data
public class FindMembersResponse {
    @JsonValue
    List<FindMemberResponse> members;

    public FindMembersResponse(List<FindMemberResponse> members) {
        this.members = members;
    }
}
