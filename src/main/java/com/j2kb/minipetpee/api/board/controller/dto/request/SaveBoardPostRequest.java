package com.j2kb.minipetpee.api.board.controller.dto.request;

import com.j2kb.minipetpee.api.board.domain.BoardPost;
import com.j2kb.minipetpee.api.setting.domain.Tab;
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

    public BoardPost toEntity(Tab tab) {
        return BoardPost.builder()
                .title(getTitle())
                .tab(tab)
                .content(getContent())
                .build();
    }
}

