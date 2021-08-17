package com.j2kb.minipetpee.api.album.controller.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AlbumPostImageResponse {
    private final Long id;
    private final String url;
}
