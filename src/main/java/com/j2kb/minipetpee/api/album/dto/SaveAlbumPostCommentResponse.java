package com.j2kb.minipetpee.api.album.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SaveAlbumPostCommentResponse {
    private int id;
    private String content;
    private AlbumPostCommentMember memberInfo;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    public SaveAlbumPostCommentResponse(int id, String content, AlbumPostCommentMember memberInfo, LocalDateTime createdAt) {
        this.id = id;
        this.memberInfo = memberInfo;
        this.content = content;
        this.createdAt = createdAt;
    }
}
