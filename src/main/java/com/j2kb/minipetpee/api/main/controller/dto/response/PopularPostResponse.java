package com.j2kb.minipetpee.api.main.controller.dto.response;

import com.j2kb.minipetpee.global.domain.Post;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PopularPostResponse {
    private final Long homepeeId;
    private final PostResponse post;
    private final MemberResponse member;

    public PopularPostResponse(Post post) {
        this.homepeeId = post.homepeeId();
        this.post = new PostResponse(post);
        this.member = new MemberResponse(post.member());
    }
}
