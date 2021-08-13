package com.j2kb.minipetpee.star.controller.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import java.util.List;

@Data
public class FindAllFansResponse {

    @JsonValue
    List<FindFanResponse> fans;

    public FindAllFansResponse(List<FindFanResponse> fans) {
        this.fans = fans;
    }
}
