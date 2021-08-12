package com.j2kb.minipetpee.api.album.dto;

import lombok.Data;

@Data
public class AlbumImageDto {
    private int id;
    private String url;

    public AlbumImageDto(int id, String url) {
        this.id = id;
        this.url = url;
    }
}
