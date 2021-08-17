package com.j2kb.minipetpee.api.album.controller.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AlbumPostCommentMemberResponse {
    private final Long id;
    private final String name;
}
