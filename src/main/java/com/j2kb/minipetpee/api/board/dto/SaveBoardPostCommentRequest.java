package com.j2kb.minipetpee.api.board.dto;

import lombok.Data;

@Data
public class SaveBoardPostCommentRequest {
    private int memberId;
    private String content;
}
