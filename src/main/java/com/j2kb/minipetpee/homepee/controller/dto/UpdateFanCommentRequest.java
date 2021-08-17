package com.j2kb.minipetpee.homepee.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateFanCommentRequest {
    private final Long id;
    private final Long memberId;
    private final String content;
}
