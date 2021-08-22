package com.j2kb.minipetpee.api.board.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SaveBoardPostRequest {
    private String title;
    private String content;
    private String image;
    private boolean visible;
}