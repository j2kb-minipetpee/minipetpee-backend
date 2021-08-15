package com.j2kb.minipetpee.api.album.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SaveAlbumPostRequest {
    private String title;
    private List<SaveAlbumPostImageRequest> images;
    private boolean visible;

}
