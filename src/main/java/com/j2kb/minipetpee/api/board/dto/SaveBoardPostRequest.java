package com.j2kb.minipetpee.api.board.dto;

import lombok.Data;

@Data
public class SaveBoardPostRequest {
    private String title;
    private String content;
    private BoardPostImage image;
    private boolean visible;

    public SaveBoardPostRequest(String title, String content, BoardPostImage image, boolean visible) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.visible = visible;
    }
}
