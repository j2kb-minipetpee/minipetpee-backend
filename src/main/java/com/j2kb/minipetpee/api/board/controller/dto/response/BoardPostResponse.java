package com.j2kb.minipetpee.api.board.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.dto.CommentPaginationResponse;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@RequiredArgsConstructor
public class BoardPostResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final int viewCount;
    private final BoardPostImageResponse image;
    private final CommentPaginationResponse comment;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    public BoardPostResponse(BoardPageResult boardPost) {

        if(Objects.isNull(boardPost.getPage())){
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMP0001);
        }

        this.id = boardPost.id();
        this.title = boardPost.title();
        this.content = boardPost.content();
        this.viewCount = boardPost.viewCount();
        this.image = new BoardPostImageResponse(boardPost.boardPost());
        this.comment = new CommentPaginationResponse(boardPost.comments());
        this.createdAt = boardPost.createdAt();
    }
}
