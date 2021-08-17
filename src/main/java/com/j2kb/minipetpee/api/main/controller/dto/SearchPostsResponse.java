package com.j2kb.minipetpee.api.main.controller.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class SearchPostsResponse {
    @JsonValue
    private final List<SearchPostResponse> posts;
}
