package com.j2kb.minipetpee.main.controller.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import java.util.List;

@Data
public class FindAllPopularPostsResponse {

    @JsonValue
    List<FindPopularPostResponse> popularPosts;

    public FindAllPopularPostsResponse(List<FindPopularPostResponse> popularPosts) {
        this.popularPosts = popularPosts;
    }
}
