package com.j2kb.minipetpee.homepee.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class FanCommentResponse {
    private final Long id;
    private final Long memberId;
    private final String memberName;
    private final String content;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private final LocalDateTime createdAt;

}
