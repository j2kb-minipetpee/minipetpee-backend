package com.j2kb.minipetpee.main.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FindPopularPostResponse {
    private final long homepeeId;
    private final PostResponse post;
    private final PopularPostMemberResponse member;
}
