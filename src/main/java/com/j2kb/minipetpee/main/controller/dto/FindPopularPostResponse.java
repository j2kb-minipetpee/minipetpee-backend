package com.j2kb.minipetpee.main.controller.dto;

import lombok.Data;

@Data
public class FindPopularPostResponse {
    private long homepeeId;
    private PostResponse post;
    private PopularPostMemberResponse member;

    public FindPopularPostResponse(long homepeeId, PostResponse post, PopularPostMemberResponse member) {
        this.homepeeId = homepeeId;
        this.post = post;
        this.member = member;
    }
}
