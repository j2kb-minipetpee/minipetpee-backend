package com.j2kb.minipetpee.api.album.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.j2kb.minipetpee.global.domain.Comment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class SaveAlbumPostCommentResponse {
    private final Long id;
    private final String content;
    private final AlbumPostCommentMemberResponse member;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    public SaveAlbumPostCommentResponse(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.member = new AlbumPostCommentMemberResponse(comment);
        this.createdAt = comment.getCreatedAt();
    }
}
