package com.j2kb.minipetpee.homepee.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class FindHomepeeResponse {
    private final ProfileResponse profile;
    private final String gateImageUrl;
    private final List<RecentPostResponse> recentPosts;
    private final String title;
    private final int visitCount;
    private final List<FanCommentResponse> fanComments;
}
