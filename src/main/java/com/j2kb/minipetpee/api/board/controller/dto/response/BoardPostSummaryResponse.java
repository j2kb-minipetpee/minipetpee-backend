package com.j2kb.minipetpee.api.board.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.j2kb.minipetpee.api.board.domain.BoardPost;
import com.j2kb.minipetpee.global.domain.Post;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class BoardPostSummaryResponse {
    private final Long id;
    private final String title;
    private final BoardPostImageResponse image;
    private final Integer viewCount;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    public BoardPostSummaryResponse(Post boardPost) {
        this.id = boardPost.getId();
        this.title = boardPost.getTitle();
        this.image = new BoardPostImageResponse(boardPost);
        this.viewCount = boardPost instanceof BoardPost ? ((BoardPost) boardPost).getViewCount() : null;
        this.createdAt = boardPost.getCreatedAt();
    }
}
