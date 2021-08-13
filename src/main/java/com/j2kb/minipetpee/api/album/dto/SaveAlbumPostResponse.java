package com.j2kb.minipetpee.api.album.dto;

import lombok.Data;

@Data
public class SaveAlbumPostResponse {
    private int id;

    public SaveAlbumPostResponse(int id) {
        this.id = id;
    }
}
