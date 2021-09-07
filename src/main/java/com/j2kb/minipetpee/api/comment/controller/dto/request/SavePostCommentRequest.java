package com.j2kb.minipetpee.api.comment.controller.dto.request;

import com.j2kb.minipetpee.api.comment.domain.Comment;
import com.j2kb.minipetpee.api.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SavePostCommentRequest {

    @NotNull(message = "EMP10001")
    @Size(min = 1, max = 200, message = "EMP10002")
    private String content;

    public Comment toEntity(Member member) {
        return Comment.builder()
                .content(this.content)
                .member(member)
                .build();
    }
}
