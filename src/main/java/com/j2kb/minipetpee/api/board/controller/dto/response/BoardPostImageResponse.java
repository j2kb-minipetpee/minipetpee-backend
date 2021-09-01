package com.j2kb.minipetpee.api.board.controller.dto.response;

import com.j2kb.minipetpee.api.board.domain.BoardPost;
import com.j2kb.minipetpee.global.domain.Post;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class BoardPostImageResponse {
    private final Long id;
    private final String url;

    public BoardPostImageResponse(Post boardPost) {
        this.id = boardPost.imageId();
        this.url = boardPost.imageUrl();
    }
}
