package com.j2kb.minipetpee.api.album.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateAlbumPostImageRequest {
    private Long id;
    private String url;
}
