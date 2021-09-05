package com.j2kb.minipetpee.api.board.controller.dto.response;

import com.j2kb.minipetpee.api.board.domain.BoardPost;
import com.j2kb.minipetpee.api.comment.domain.Comment;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.domain.Post;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class BoardPageResult {
    private final BoardPost boardPost;
    private final Page<Comment> page;

    public BoardPageResult(BoardPost boardPost, Page<Comment> page) {
        this.boardPost = boardPost;
        this.page = page;
    }

    public Page<Comment> comments() {
        if(Objects.isNull(page)) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMP0001);
        }
        return this.page;
    }

    public BoardPost boardPost() {
        if(Objects.isNull(boardPost)) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMP0001);
        }
        return this.boardPost;
    }

    public Long id() {
        if(Objects.isNull(boardPost)) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMP0001);
        }
        return boardPost.getId();
    }

    public String content() {
        if(Objects.isNull(boardPost)) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMP0001);
        }
        return boardPost.getContent();
    }

    public String title() {
        if(Objects.isNull(boardPost)) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMP0001);
        }
        return boardPost.getTitle();
    }

    public int viewCount() {
        if(Objects.isNull(boardPost)) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMP0001);
        }
        return boardPost.getViewCount();
    }

    public LocalDateTime createdAt() {
        if(Objects.isNull(boardPost)) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMP0001);
        }
        return boardPost.getCreatedAt();
    }

}
