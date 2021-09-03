package com.j2kb.minipetpee.api.album.controller.dto.response;

import com.j2kb.minipetpee.global.domain.Image;
import lombok.Getter;

@Getter
public class AlbumPostImageResponse {
    private final Long id;
    private final String url;

    public AlbumPostImageResponse(Image img) {
        this.id = img.getId();
        this.url = img.getUrl();
    }
}
