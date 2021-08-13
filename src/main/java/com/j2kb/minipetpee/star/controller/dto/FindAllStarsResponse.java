package com.j2kb.minipetpee.star.controller.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
public class FindAllStarsResponse {

    @JsonValue
    private final List<FindStarResponse> stars;

    public FindAllStarsResponse(List<FindStarResponse> stars) {
        this.stars = stars;
    }
}
