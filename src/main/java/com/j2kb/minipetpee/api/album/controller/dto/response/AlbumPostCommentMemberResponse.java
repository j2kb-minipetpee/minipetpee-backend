package com.j2kb.minipetpee.api.album.controller.dto.response;

import com.j2kb.minipetpee.global.domain.Comment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class AlbumPostCommentMemberResponse {
    private final Long id;
    private final String name;

    public AlbumPostCommentMemberResponse(Comment comment) {
        this.id = comment.memberId();
        this.name = comment.memberName();
    }
}
