package com.j2kb.minipetpee.api.album.controller.dto.response;

import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.dto.CommentPaginationResponse;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.Getter;
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

    public AlbumPostResponse(AlbumResult albumResult) {
        if(Objects.isNull(albumResult.getAlbumPost())) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMP0001);
        }
        if(Objects.isNull(albumResult.getAlbumPost().getImages())) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMP0001);
        }
        if(Objects.isNull(albumResult.getAlbumComment())) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMP0001);
        }

        this.id = albumResult.getAlbumPost().getId();
        this.title = albumResult.getAlbumPost().getTitle();
        this.images = albumResult.getAlbumPost().getImages()
                .stream()
                .map(AlbumPostImageResponse::new)
                .collect(Collectors.toList());
        this.comments = new CommentPaginationResponse(albumResult.getAlbumComment());
    }
}
