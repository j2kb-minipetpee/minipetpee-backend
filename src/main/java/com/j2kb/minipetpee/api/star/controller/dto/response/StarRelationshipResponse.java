package com.j2kb.minipetpee.api.star.controller.dto.response;

import lombok.Getter;

@Getter
public class StarRelationshipResponse {
    private final Long fanMemberId;
    private final Long starMemberId;
    private final boolean stars;

    public StarRelationshipResponse(Long fanMemberId, Long starMemberId, boolean stars) {
        this.fanMemberId = fanMemberId;
        this.starMemberId = starMemberId;
        this.stars = stars;
    }
}
