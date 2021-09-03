package com.j2kb.minipetpee.api.comment.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.j2kb.minipetpee.api.comment.domain.Comment;
import com.j2kb.minipetpee.global.dto.CommentMemberResponse;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SavePostCommentResponse {
    private final Long id;
    private final String content;
    private final CommentMemberResponse member;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    public SavePostCommentResponse(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.member = new CommentMemberResponse(comment);
        this.createdAt = comment.getCreatedAt();
    }
}
