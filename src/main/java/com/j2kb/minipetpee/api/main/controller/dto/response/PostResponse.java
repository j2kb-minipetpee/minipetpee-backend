package com.j2kb.minipetpee.api.main.controller.dto.response;

import com.j2kb.minipetpee.global.domain.Post;
import lombok.Getter;

@Getter
public class PostResponse {
    private final Long homepeeId;
    private final PostDetailResponse post;
    private final MemberResponse member;

    public PostResponse(Post post) {

        this.homepeeId = post.homepeeId();
        this.post = new PostDetailResponse(post);
        this.member = new MemberResponse(post.member());
    }
}
