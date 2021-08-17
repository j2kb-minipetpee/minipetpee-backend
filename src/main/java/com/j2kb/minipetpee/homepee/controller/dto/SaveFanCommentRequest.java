package com.j2kb.minipetpee.homepee.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SaveFanCommentRequest {
    private final Long memberId;
    private final String content;
}
