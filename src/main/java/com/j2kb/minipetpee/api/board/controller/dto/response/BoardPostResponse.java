package com.j2kb.minipetpee.api.board.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.j2kb.minipetpee.api.board.domain.BoardPost;
import com.j2kb.minipetpee.api.comment.domain.Comment;
import com.j2kb.minipetpee.global.domain.Post;
import com.j2kb.minipetpee.global.dto.CommentPaginationResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class BoardPostResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final int viewCount;
    private final BoardPostImageResponse image;
    private final CommentPaginationResponse comment;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    public BoardPostResponse(Post boardPost, Page<Comment> comments) {
        this.id = boardPost.getId();
        this.title = boardPost.getTitle();
        this.content = boardPost instanceof BoardPost? ((BoardPost) boardPost).getContent() : null;
        this.viewCount = boardPost instanceof BoardPost? ((BoardPost) boardPost).getViewCount() : null;
        this.image = new BoardPostImageResponse(boardPost);
        this.comment = new CommentPaginationResponse(comments);
        this.createdAt = boardPost.getCreatedAt();
    }
}
