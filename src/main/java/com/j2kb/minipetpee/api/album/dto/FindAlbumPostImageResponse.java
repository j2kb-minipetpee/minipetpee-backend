package com.j2kb.minipetpee.api.album.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FindAlbumPostImageResponse {
    private final Long id;
    private final String url;
}
