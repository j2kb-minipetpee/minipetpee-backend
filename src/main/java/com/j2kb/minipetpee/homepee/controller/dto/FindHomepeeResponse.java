package com.j2kb.minipetpee.homepee.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FindHomepeeResponse {
    private final ProfileResponse profile;
    private final String gateImageUrl;
    private final RecentPostsResponse recentPosts;
    private final String title;
    private final int visitCount;
    private final FanCommentsResponse fanComments;
}
