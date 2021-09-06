package com.j2kb.minipetpee.api.star.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.j2kb.minipetpee.api.star.domain.Star;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class StarResponse {
    private final long id;
    private final long memberId;
    private final String name;
    private final String profileImageUrl;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    public StarResponse(Star star){
        this.id = star.getId();
        this.memberId = star.starMemberId();
        this.name = star.starMemberName();
        this.profileImageUrl = star.starMemberProfileImageUrl();
        this.createdAt = star.getCreatedAt();
    }
}
