package com.j2kb.minipetpee.api.board.dto;

import lombok.Data;

@Data
public class FindBoardPostImage {
    private int id;
    private String url;

    public FindBoardPostImage(int id, String url) {
        this.id = id;
        this.url = url;
    }
}
