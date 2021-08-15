package com.j2kb.minipetpee.api.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class FindBoardPostResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final int viewCount;
    private final BoardPostImageResponse image;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime createdAt;
}
