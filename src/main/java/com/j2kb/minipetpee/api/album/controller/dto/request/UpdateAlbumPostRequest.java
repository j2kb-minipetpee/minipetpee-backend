package com.j2kb.minipetpee.api.album.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateAlbumPostRequest {
    private Long id;
    private String title;
    private List<UpdateAlbumPostImageRequest> images;
    private boolean visible;
}
