package com.j2kb.minipetpee.api.album.dto;

import lombok.Data;

import java.util.List;

@Data
public class FindAlbumPostListResponse {
    private int id;
    private String title;
    private List<FindAlbumPostImage> images;
    private int viewCount;
    private boolean visible;

    public FindAlbumPostListResponse(int id, String title, List<FindAlbumPostImage> images, int viewCount, boolean visible) {
        this.id = id;
        this.title = title;
        this.images = images;
        this.viewCount = viewCount;
        this.visible = visible;
    }
}
