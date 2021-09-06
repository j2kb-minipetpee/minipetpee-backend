package com.j2kb.minipetpee.api.homepee.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.j2kb.minipetpee.api.fancomment.domain.FanComment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class HomepeeFanCommentResponse {
    private final Long id;
    private final Long memberId;
    private final String memberName;
    private final String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    public HomepeeFanCommentResponse(FanComment fanComment) {
        this.id = fanComment.getId();
        this.memberId = fanComment.memberId();
        this.memberName = fanComment.memberName();
        this.content = fanComment.getContent();
        this.createdAt = fanComment.getCreatedAt();
    }
}
