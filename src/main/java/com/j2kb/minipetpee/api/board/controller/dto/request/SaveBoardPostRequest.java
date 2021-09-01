package com.j2kb.minipetpee.api.board.controller.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaveBoardPostRequest {

    @NotNull(message = "EMP4002")
    @Size(min = 1, max = 20, message = "EMP4003")
    private String title;

    @NotNull(message = "EMP4004")
    @Size(min = 1, max = 500, message = "EMP4005")
    private String content;

    private String image;

    private boolean visible;
}
