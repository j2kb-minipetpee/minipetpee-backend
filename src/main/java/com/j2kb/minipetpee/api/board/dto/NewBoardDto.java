package com.j2kb.minipetpee.api.board.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class NewBoardDto {

    private String title;
    private String content;
    private ImageDto image;
    private boolean visible;

    @JsonCreator
    public NewBoardDto(String title,  String content, ImageDto image, boolean visible) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.visible = visible;
    }
}
