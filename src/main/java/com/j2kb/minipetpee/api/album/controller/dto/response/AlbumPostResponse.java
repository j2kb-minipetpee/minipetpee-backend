package com.j2kb.minipetpee.api.album.controller.dto.response;

import com.j2kb.minipetpee.api.album.domain.AlbumPost;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.domain.Comment;
import com.j2kb.minipetpee.global.dto.CommentPaginationResponse;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class AlbumPostResponse {
    private final Long id;
    private final String title;
    private final List<AlbumPostImageResponse> images;
    private final CommentPaginationResponse comments;

    public AlbumPostResponse(AlbumPost albumPost, Page<Comment> albumComments) {
        this.id = albumPost.getId();
        this.title = albumPost.getTitle();

        if(Objects.isNull(albumPost.getImages())) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMP0001);
        }
        this.images = albumPost.getImages()
                .stream()
                .map(AlbumPostImageResponse::new)
                .collect(Collectors.toList());

        if(Objects.isNull(albumComments)) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMP0001);
        }
        this.comments = new CommentPaginationResponse(albumComments);
    }
}
