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
    private List<AlbumPostCommentResponse> comments;

    public AlbumPostResponse(AlbumPost albumPost) {
        this.id = albumPost.getId();
        this.title = albumPost.getTitle();
        if(!Objects.isNull(albumPost.getImages())) {
            this.images = albumPost.getImages()
                    .stream()
                    .map(AlbumPostImageResponse::new)
                    .collect(Collectors.toList());
        }
        if(!Objects.isNull(albumPost.getComments())) {
            this.comments = albumPost.getComments()
                    .stream()
                    .map(AlbumPostCommentResponse::new)
                    .collect(Collectors.toList());
        }
    }
}
