package com.j2kb.minipetpee.api.board.dto;

import lombok.Data;

@Data
public class SaveBoardPostResponse {
    private int id;

    public SaveBoardPostResponse(int id) {
        this.id = id;
    }
}
