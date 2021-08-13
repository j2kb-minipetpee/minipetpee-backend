package com.j2kb.minipetpee.main.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostInfo {
    private long id;
    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createdAt;

    private String imageUrl;
    private String content;

    public PostInfo(long id, String title, LocalDateTime createdAt, String imageUrl, String content) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.imageUrl = imageUrl;
        this.content = content;
    }
}
