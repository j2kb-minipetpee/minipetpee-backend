package com.j2kb.minipetpee.api.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SavedCommentDto {
    private int id;
    private int memberId;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    public SavedCommentDto(int id, int memberId, String content, LocalDateTime createdAt) {
        this.id = id;
        this.memberId = memberId;
        this.content = content;
        this.createdAt = createdAt;
    }
}
