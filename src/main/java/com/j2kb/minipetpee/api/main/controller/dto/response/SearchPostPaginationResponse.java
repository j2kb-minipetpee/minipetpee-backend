package com.j2kb.minipetpee.api.main.controller.dto.response;

import com.j2kb.minipetpee.api.board.domain.BoardPost;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.dto.PageResponse;
import com.j2kb.minipetpee.global.domain.Post;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class SearchPostPaginationResponse {
    private final List<SearchPostResponse> content;
    private final PageResponse page;

    public SearchPostPaginationResponse(Page<BoardPost> searchPosts) {
        if (Objects.isNull(searchPosts)) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMP0001);
        }
        this.content = searchPosts
                .stream()
                .map(SearchPostResponse::new)
                .collect(Collectors.toList());
        this.page = new PageResponse(searchPosts);
    }
}
