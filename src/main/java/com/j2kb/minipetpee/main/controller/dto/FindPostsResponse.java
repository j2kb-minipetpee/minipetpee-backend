package com.j2kb.minipetpee.main.controller.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import java.util.List;

@Data
public class FindPostsResponse {
    @JsonValue
    List<FindPostResponse> posts;

    public FindPostsResponse(List<FindPostResponse> posts) {
        this.posts = posts;
    }
}
