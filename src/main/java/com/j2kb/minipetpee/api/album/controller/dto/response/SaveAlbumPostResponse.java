package com.j2kb.minipetpee.api.album.controller.dto.response;

import com.j2kb.minipetpee.api.album.domain.AlbumPost;
import lombok.Getter;

@Getter
public class SaveAlbumPostResponse {
    private final Long id;

    public SaveAlbumPostResponse(AlbumPost albumPost) {
        this.id = albumPost.getId();
    }
}
