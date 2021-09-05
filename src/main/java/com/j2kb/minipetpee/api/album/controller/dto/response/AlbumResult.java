package com.j2kb.minipetpee.api.album.controller.dto.response;

import com.j2kb.minipetpee.api.album.domain.AlbumPost;
import com.j2kb.minipetpee.api.comment.domain.Comment;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.domain.Image;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;

@Getter
public class AlbumResult {
    private final AlbumPost albumPost;
    private final Page<Comment> albumComment;

    public AlbumResult(AlbumPost albumPost,Page<Comment> albumComment) {
        this.albumPost = albumPost;
        this.albumComment = albumComment;
    }

    public Page<Comment> albumComment() {
        if(Objects.isNull(albumComment)) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMP0001);
        }
        return albumComment;
    }

    public AlbumPost albumPost() {
        if(Objects.isNull(albumPost)) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMP0001);
        }
        return albumPost;
    }

    public Long id() {
        if(Objects.isNull(albumPost)) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMP0001);
        }
        return albumPost.getId();
    }

    public String title() {
        if(Objects.isNull(albumPost)) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMP0001);
        }
        return albumPost.getTitle();
    }

    public List<Image> images() {
        if(Objects.isNull(albumPost)) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMP0001);
        }
        if(Objects.isNull(albumPost.getImages())) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMP0001);
        }
        return albumPost.getImages();
    }
}
