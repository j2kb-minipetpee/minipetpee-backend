package com.j2kb.minipetpee.api.board.dto;

import lombok.Data;

@Data
public class UpdateBoardDto {
    private int id;
    private String title;
    private String content;
    private BoardPostImage image;
}
