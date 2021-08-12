package com.j2kb.minipetpee.api.board.dto;

import lombok.Data;

@Data
public class RequestBoardDto {
    private int id;

    public RequestBoardDto(int id) {
        this.id = id;
    }
}
