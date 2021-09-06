package com.j2kb.minipetpee.api.star.controller.dto.response;

import com.j2kb.minipetpee.security.jwt.JwtAuthenticationPrincipal;
import lombok.Getter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Getter
public class StarRelationshipResponse {
    private final Long fanMemberId;
    private final Long starMemberId;
    private final boolean stars;

    public StarRelationshipResponse(JwtAuthenticationPrincipal principal, Long starMemberId, boolean stars) {
        this.fanMemberId = principal.getId();
        this.starMemberId = starMemberId;
        this.stars = stars;
    }
}
