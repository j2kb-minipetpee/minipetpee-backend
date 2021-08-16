package com.j2kb.minipetpee.api.board.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BoardPostCommentMemberResponse {
    private final Long id;
    private final String name;
}
