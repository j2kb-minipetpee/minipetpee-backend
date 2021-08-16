package com.j2kb.minipetpee.api.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SaveBoardPostRequest {
    private String title;
    private String content;
    private SaveBoardPostImageRequest image;
    private boolean visible;
}
