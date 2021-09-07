package com.j2kb.minipetpee.api.album.controller.dto.response;

import com.j2kb.minipetpee.global.dto.CommentPaginationResponse;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AlbumPostResponse {
    private final Long id;
    private final String title;
    private final List<AlbumPostImageResponse> images;
    private final CommentPaginationResponse comments;

    public AlbumPostResponse(AlbumResult albumResult) {

        this.id = albumResult.id();
        this.title = albumResult.title();
        this.images = albumResult.images()
                .stream()
                .map(AlbumPostImageResponse::new)
                .collect(Collectors.toList());
        this.comments = new CommentPaginationResponse(albumResult.albumComment());
    }
}
