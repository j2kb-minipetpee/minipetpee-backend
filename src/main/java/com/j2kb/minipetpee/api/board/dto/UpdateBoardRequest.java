package com.j2kb.minipetpee.api.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateBoardRequest {
    private Long id;
    private String title;
    private String content;
    private BoardPostImageResponse image;
}
