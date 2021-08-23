package com.j2kb.minipetpee.api.main.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PopularPostResponse {
    private final Long homepeeId;
    private final PostResponse post;
    private final PopularPostMemberResponse member;

    public PopularPostResponse(Long homepeeId, PostResponse post, PopularPostMemberResponse member) {
        this.homepeeId = homepeeId;
        this.post = post;
        this.member = member;
    }
}
