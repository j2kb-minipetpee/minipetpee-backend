package com.j2kb.minipetpee.api.board.controller.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaveBoardPostCommentRequest {
    private Long memberId;
    private String content;
}
