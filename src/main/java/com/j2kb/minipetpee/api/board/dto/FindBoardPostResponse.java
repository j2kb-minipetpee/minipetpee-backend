package com.j2kb.minipetpee.api.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FindBoardPostResponse {
    private int id;
    private String title;
    private String content;
    private int viewCount;
    private BoardPostImage image;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    public FindBoardPostResponse(int id, String title, String content, int viewCount, BoardPostImage image, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.image = image;
        this.createdAt = createdAt;
    }
}
