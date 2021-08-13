package com.j2kb.minipetpee.api.album.dto;

import lombok.Data;

@Data
public class FindAlbumPostImage {
    private int id;
    private String url;

    public FindAlbumPostImage(int id, String url) {
        this.id = id;
        this.url = url;
    }
}
