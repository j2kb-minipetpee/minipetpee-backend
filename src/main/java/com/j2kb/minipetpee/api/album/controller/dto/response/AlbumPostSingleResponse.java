package com.j2kb.minipetpee.api.album.controller.dto.response;

import com.j2kb.minipetpee.api.album.domain.AlbumPost;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AlbumPostSingleResponse {
    private final String title;
    private final List<AlbumPostImageResponse> images;

    public AlbumPostSingleResponse(AlbumPost albumPost) {
        this.title = albumPost.getTitle();
        this.images = albumPost.getImages()
                .stream()
                .map(AlbumPostImageResponse::new)
                .collect(Collectors.toList());
    }
}
