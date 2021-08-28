package com.j2kb.minipetpee.api.album.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.j2kb.minipetpee.global.domain.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AlbumPostCommentResponse {
    private final Long id;
    private final AlbumPostCommentMemberResponse member;
    private final String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    public AlbumPostCommentResponse(Comment comment) {
        this.id = comment.getId();
        this.member = new AlbumPostCommentMemberResponse(comment);
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
    }
}
