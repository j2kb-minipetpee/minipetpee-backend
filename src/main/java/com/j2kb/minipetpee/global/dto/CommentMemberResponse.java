package com.j2kb.minipetpee.global.dto;

import com.j2kb.minipetpee.global.domain.Comment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class CommentMemberResponse {
    private final Long id;
    private final String name;

    public CommentMemberResponse(Comment comment) {
        this.id = comment.memberId();
        this.name = comment.memberName();
    }
}