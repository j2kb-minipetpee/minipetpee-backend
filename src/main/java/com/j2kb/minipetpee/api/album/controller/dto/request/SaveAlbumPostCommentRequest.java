package com.j2kb.minipetpee.api.album.controller.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@NoArgsConstructor
public class SaveAlbumPostCommentRequest {

    @NotNull(message = "EMP2003")
    private Long memberId;

    @NotNull(message = "EMP10001")
    @Size(min = 1, max = 200, message = "EMP10002")
    private String content;
}
