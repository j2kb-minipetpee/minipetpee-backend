package com.j2kb.minipetpee.global.dto;

import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.domain.Comment;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class CommentPaginationResponse {
    private final List<CommentResponse> content;
    private final PageResponse page;

    public CommentPaginationResponse(Page<Comment> comment) {
        if (Objects.isNull(comment)) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMP0001);
        }
        this.content = comment.stream()
                .map(CommentResponse::new)
                .collect(Collectors.toList());
        this.page = new PageResponse(comment);
    }
}
