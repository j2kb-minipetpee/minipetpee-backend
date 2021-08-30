package com.j2kb.minipetpee.api.main.controller.dto.response;

import com.j2kb.minipetpee.api.board.domain.BoardPost;
import com.j2kb.minipetpee.global.domain.Post;
import lombok.Getter;

@Getter
public class PopularPostResponse {
    private final Long homepeeId;
    private final PostResponse post;
    private final MemberResponse member;
    private final Integer viewCount;

    public PopularPostResponse(BoardPost post) {
        this.homepeeId = post.homepeeId();
        this.post = new PostResponse(post);
        this.member = new MemberResponse(post.member());
        this.viewCount = post.getViewCount();
    }
}
