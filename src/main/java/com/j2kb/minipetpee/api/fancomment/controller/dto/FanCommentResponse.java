package com.j2kb.minipetpee.api.fancomment.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.j2kb.minipetpee.api.fancomment.domain.FanComment;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class FanCommentResponse {
    private final Long id;
    private final Long memberId;
    private final String memberName;
    private final String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    public FanCommentResponse(FanComment fanComment) {
        this.id = fanComment.getId();
        this.memberId = fanComment.memberId();
        this.memberName = fanComment.memberName();
        this.content = fanComment.getContent();
        this.createdAt = fanComment.getCreatedAt();
    }
}
