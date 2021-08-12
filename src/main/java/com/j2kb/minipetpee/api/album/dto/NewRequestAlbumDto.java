package com.j2kb.minipetpee.api.album.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.j2kb.minipetpee.api.board.dto.ImageDto;
import lombok.Data;

import java.util.List;

@Data
public class NewRequestAlbumDto {
    private String title;
    private List<ImageDto> imageList;
    private boolean visible;

    @JsonCreator
    public NewRequestAlbumDto(String title, List<ImageDto> imageList, boolean visible) {
        this.title = title;
        this.imageList = imageList;
        this.visible = visible;
    }
}
