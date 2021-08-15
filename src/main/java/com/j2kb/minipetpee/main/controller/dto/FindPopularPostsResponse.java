package com.j2kb.minipetpee.main.controller.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import java.util.List;

@Data
public class FindPopularPostsResponse {

    @JsonValue
    List<FindPopularPostResponse> popularPosts;

    public FindPopularPostsResponse(List<FindPopularPostResponse> popularPosts) {
        this.popularPosts = popularPosts;
    }
}
