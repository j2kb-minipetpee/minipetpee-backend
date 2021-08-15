package com.j2kb.minipetpee.main.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PopularPostMemberResponse {
    private final long id;
    private final String name;
    private final String profileImageUrl;
}