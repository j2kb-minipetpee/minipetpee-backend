package com.j2kb.minipetpee.api.album.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class FindAlbumPostsResponse {

    @JsonValue
    private final List<FindAlbumPostResponse> albums;
}
