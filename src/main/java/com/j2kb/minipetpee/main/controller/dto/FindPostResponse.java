package com.j2kb.minipetpee.main.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FindPostResponse {
    private final String memberName;
    private final PostResponse postInfo;
}
