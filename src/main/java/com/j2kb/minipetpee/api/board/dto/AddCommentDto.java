package com.j2kb.minipetpee.api.board.dto;

import lombok.Data;

@Data
public class AddCommentDto {
    private int memberId;
    private String content;
}
