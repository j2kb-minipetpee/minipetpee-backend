package com.j2kb.minipetpee.api.album.controller.dto.response;

import com.j2kb.minipetpee.api.album.domain.AlbumPost;
import lombok.Getter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class AlbumPostResponse {
    private final Long id;
    private final String title;
    private List<AlbumPostImageResponse> images;

    public AlbumPostResponse(AlbumPost albumPost) {
        this.id = albumPost.getId();
        this.title = albumPost.getTitle();
        if(!Objects.isNull(albumPost.getImages())) {
            this.images = albumPost.getImages()
                    .stream()
                    .map(AlbumPostImageResponse::new)
                    .collect(Collectors.toList());
        }
    }
}
