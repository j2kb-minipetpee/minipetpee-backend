package com.j2kb.minipetpee.main.controller.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import java.util.List;

@Data
public class FindAllMembersResponse {
    @JsonValue
    List<FindMemberResponse> members;

    public FindAllMembersResponse(List<FindMemberResponse> members) {
        this.members = members;
    }
}
