package com.j2kb.minipetpee.api.board.controller.dto.response;

import com.j2kb.minipetpee.global.domain.Post;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class BoardPageResult {
    private final Post boardPost;
    private final Page page;

    public BoardPageResult(Post boardPost, Page page) {
        this.boardPost = boardPost;
        this.page = page;
    }
}
