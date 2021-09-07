package com.j2kb.minipetpee.api.star.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.j2kb.minipetpee.api.star.domain.Star;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FanResponse {
    private final Long id;
    private final Long memberId;
    private final String name;
    private final String profileImageUrl;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private final LocalDateTime createdAt;

    public FanResponse(Star star) {
        this.id = star.getId();
        this.memberId = star.fanMemberId();
        this.name = star.fanMemberName();
        this.profileImageUrl = star.fanMemberProfileImageUrl();
        this.createdAt = star.getCreatedAt();
    }
}
