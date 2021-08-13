package com.j2kb.minipetpee.api.album.dto;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.List;

public class FindAlbumPostResponse {

    @JsonValue
    private final List<FindAlbumPostListResponse> albums;

    public FindAlbumPostResponse(List<FindAlbumPostListResponse> albums) {
        this.albums = albums;
    }
}
