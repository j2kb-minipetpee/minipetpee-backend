package com.j2kb.minipetpee.homepee.controller.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class FanCommentsResponse {

    @JsonValue
    private final List<FanCommentResponse> fanComments;
}