package com.j2kb.minipetpee.homepee.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class SaveFanCommentRequest {
    private Long memberId;
    private String content;
}
