package com.j2kb.minipetpee.api.album.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class FindAlbumPostResponse {
    private final Long id;
    private final String title;
    private final List<FindAlbumPostImageResponse> images;
    private final int viewCount;
    private final boolean visible;
}
