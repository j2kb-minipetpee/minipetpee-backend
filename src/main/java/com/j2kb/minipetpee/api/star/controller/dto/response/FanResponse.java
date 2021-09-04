package com.j2kb.minipetpee.api.star.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.j2kb.minipetpee.api.star.domain.Star;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class FanResponse {
    private final Long id;
    private final Long memberId;
    private final String name;
    private final String profileImageUrl;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private final LocalDateTime createdAt;

    public FanResponse(Star star) {
        this.id = star.getId();
        this.memberId = star.getFanMember().getId(); //수정하기
        this.name = star.getFanMember().name(); //수정하기
        this.profileImageUrl = star.getFanMember().profileImageUrl(); //수정하기
        this.createdAt = star.getCreatedAt();
    }
}
