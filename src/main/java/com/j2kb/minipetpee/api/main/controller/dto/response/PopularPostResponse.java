package com.j2kb.minipetpee.api.main.controller.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PopularPostResponse {
    private final Long homepeeId;
    private final PostResponse post;
    private final MemberResponse member;

    public PopularPostResponse(Long homepeeId, PostResponse post, MemberResponse member) {
        this.homepeeId = homepeeId;
        this.post = post;
        this.member = member;
    }
}
