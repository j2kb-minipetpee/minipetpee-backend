package com.j2kb.minipetpee.api.album.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AlbumListDto {
    private int id;
    private String title;
    private List<AlbumImageDto> images = new ArrayList<>();
    private int viewCount;
    private boolean visible;

    public AlbumListDto(int id, String title, List<AlbumImageDto> images, int viewCount, boolean visible) {
        this.id = id;
        this.title = title;
        this.images = images;
        this.viewCount = viewCount;
        this.visible = visible;
    }
}
