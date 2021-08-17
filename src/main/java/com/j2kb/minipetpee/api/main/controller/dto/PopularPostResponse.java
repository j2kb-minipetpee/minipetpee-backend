package com.j2kb.minipetpee.api.main.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PopularPostResponse {
    private final Long homepeeId;
    private final PostResponse post;
    private final PopularPostMemberResponse member;
}
