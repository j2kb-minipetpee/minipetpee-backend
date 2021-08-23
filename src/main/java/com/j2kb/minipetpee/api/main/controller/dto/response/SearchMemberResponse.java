package com.j2kb.minipetpee.api.main.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class SearchMemberResponse {
    private final Long id;
    private final String name;
    private String profileImageUrl;
    private final long homepeeId;
}
