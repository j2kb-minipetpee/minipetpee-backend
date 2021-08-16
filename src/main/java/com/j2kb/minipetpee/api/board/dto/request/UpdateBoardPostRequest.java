package com.j2kb.minipetpee.api.board.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateBoardPostRequest {
    private Long id;
    private String title;
    private String content;
    private UpdateBoardPostImageRequest image;
}
