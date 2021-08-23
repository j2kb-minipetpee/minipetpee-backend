package com.j2kb.minipetpee.api.main.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.j2kb.minipetpee.global.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private final Long id;
    private final String title;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private final LocalDateTime createdAt;

    private String imageUrl;
    private final String content;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.createdAt = post.getCreatedAt();
        this.imageUrl = post.getIm
        this.content = post.getPost
    }
}