package com.j2kb.minipetpee.api.guestnote.controller.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GuestNoteMemberResponse {
    private final Long id;
    private final String name;
    private final String profileImageUrl;
}
