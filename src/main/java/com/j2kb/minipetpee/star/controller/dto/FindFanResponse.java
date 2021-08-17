package com.j2kb.minipetpee.star.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class FindFanResponse {
    private final long id;
    private final long memberId;
    private final String name;
    private final String profileImageUrl;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private final LocalDateTime createdAt;
}
