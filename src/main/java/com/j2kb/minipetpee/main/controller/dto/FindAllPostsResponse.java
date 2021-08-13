package com.j2kb.minipetpee.main.controller.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import java.util.List;

@Data
public class FindAllPostsResponse {
    @JsonValue
    List<FindPostResponse> posts;

    public FindAllPostsResponse(List<FindPostResponse> posts) {
        this.posts = posts;
    }
}
