package com.j2kb.minipetpee.api.homepee.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateFanCommentRequest {
    private Long id;
    private Long memberId;
    private String content;
}
