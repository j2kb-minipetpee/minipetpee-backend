package com.j2kb.minipetpee.api.main.controller.dto.response;

import com.j2kb.minipetpee.api.board.domain.BoardPost;
import com.j2kb.minipetpee.global.domain.Post;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class SearchPostResponse {
    private final String memberName;
    private final PostResponse postInfo;

    public SearchPostResponse(BoardPost post) {
        this.memberName = post.memberName();
        this.postInfo = new PostResponse(post);
    }
}
