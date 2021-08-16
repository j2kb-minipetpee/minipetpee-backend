package com.j2kb.minipetpee.api.board.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BoardPostImageResponse {
    private final Long id;
    private final String url;
}
