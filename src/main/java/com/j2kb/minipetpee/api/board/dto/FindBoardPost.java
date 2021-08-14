package com.j2kb.minipetpee.api.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FindBoardPost {
    private int id;
    private String title;
    private FindBoardPostImage image;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    public FindBoardPost(int id, String title, FindBoardPostImage image, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.createdAt = createdAt;
    }
}
