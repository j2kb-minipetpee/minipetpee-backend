package com.j2kb.minipetpee.api.board.dto;

import lombok.Data;

@Data
public class BoardPostImage {
    private String url;

    public BoardPostImage(String url) {
        this.url = url;
    }
}
