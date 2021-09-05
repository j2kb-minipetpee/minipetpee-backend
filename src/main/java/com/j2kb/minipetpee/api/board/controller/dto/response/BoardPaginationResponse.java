package com.j2kb.minipetpee.api.board.controller.dto.response;

import com.j2kb.minipetpee.api.board.domain.BoardPost;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.domain.Post;
import com.j2kb.minipetpee.global.dto.PageResponse;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class BoardPaginationResponse {
    private final List<BoardPostSummaryResponse> content;
    private final PageResponse page;

    public BoardPaginationResponse(Page<BoardPost> boardPosts) {
        if(Objects.isNull(boardPosts)) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMP0001);
        }

        this.content = boardPosts.getContent()
                .stream()
                .map(BoardPostSummaryResponse::new)
                .collect(Collectors.toList());
        this.page = new PageResponse(boardPosts);

    }
}
