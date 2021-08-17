package com.j2kb.minipetpee.api.album.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SaveAlbumPostRequest {
    private String title;
    private List<String> images;
    private boolean visible;

}
