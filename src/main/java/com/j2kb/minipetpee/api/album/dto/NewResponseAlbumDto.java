package com.j2kb.minipetpee.api.album.dto;

import lombok.Data;

@Data
public class NewResponseAlbumDto {
    private int id;

    public NewResponseAlbumDto(int id) {
        this.id = id;
    }
}
