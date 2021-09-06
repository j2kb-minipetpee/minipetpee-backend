package com.j2kb.minipetpee.api.fancomment.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaveFanCommentRequest {
    @NotEmpty(message = "EMP11003")
    @Size(max = 200, message = "EMP11004")
    private String content;
}
