package com.j2kb.minipetpee.api.star.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class StarResponse {
    private final long id;
    private final long memberId;
    private final String name;
    private final String profileImageUrl;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;
}
