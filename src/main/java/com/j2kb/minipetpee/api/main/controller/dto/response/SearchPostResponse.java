package com.j2kb.minipetpee.api.main.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SearchPostResponse {
    private final String memberName;
    private final PostResponse postInfo;
}
