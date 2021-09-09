package com.j2kb.minipetpee.api.fancomment.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateFanCommentRequest {
    @NotNull(message = "EMP11001")
    private Long id;

    @NotNull(message = "EMP11002")
    private Long memberId;

    @NotEmpty(message = "EMP11003")
    @Size(max = 200, message = "EMP11004")
    private String content;
}
