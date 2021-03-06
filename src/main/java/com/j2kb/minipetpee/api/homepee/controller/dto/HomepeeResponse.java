package com.j2kb.minipetpee.api.homepee.controller.dto;

import com.j2kb.minipetpee.api.fancomment.domain.FanComment;
import com.j2kb.minipetpee.api.homepee.domain.Homepee;
import com.j2kb.minipetpee.api.star.domain.Relationship;
import lombok.Getter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class HomepeeResponse {
    private final ProfileResponse profile;
    private final String gateImageUrl;
    private final String title;
    private final int visitCount;
    private final Relationship relationship;

    public HomepeeResponse(Homepee homepee, Relationship relationship) {
        this.profile = new ProfileResponse(homepee.memberProfile());
        this.gateImageUrl = homepee.getGateImageUrl();
        this.title = homepee.getTitle();
        this.visitCount = homepee.getVisitCount();
        this.relationship = relationship;
    }
}
