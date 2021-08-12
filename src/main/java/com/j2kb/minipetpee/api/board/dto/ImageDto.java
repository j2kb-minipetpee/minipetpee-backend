package com.j2kb.minipetpee.api.board.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;


@JsonRootName("image")
@Data
public class ImageDto {
    private String url;

    @JsonCreator
    public ImageDto(String url) {
        this.url = url;
    }
}
