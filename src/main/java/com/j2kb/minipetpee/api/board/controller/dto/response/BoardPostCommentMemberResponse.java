package com.j2kb.minipetpee.api.board.controller.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BoardPostCommentMemberResponse {
    private final Long id;
    private final String name;
}
