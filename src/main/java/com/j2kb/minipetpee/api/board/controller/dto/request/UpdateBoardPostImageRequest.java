package com.j2kb.minipetpee.api.board.controller.dto.request;

import com.j2kb.minipetpee.global.domain.Image;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateBoardPostImageRequest {

    private Long id;

    private String url;

    public Image toEntity() {
        return new Image(this.url);
    }
}
