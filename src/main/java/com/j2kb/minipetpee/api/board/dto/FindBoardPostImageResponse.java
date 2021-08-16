package com.j2kb.minipetpee.api.board.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FindBoardPostImageResponse {
    private final Long id;
    private final String url;
}
