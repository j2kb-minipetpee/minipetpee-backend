package com.j2kb.minipetpee.api.board.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SaveBoardPostCommentRequest {
    private Long memberId;
    private String content;
}
