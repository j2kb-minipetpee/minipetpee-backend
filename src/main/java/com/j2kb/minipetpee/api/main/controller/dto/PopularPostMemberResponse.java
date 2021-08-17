package com.j2kb.minipetpee.api.main.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PopularPostMemberResponse {
    private final Long id;
    private final String name;
    private final String profileImageUrl;
}