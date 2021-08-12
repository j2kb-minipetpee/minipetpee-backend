package com.j2kb.minipetpee.api.album.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AlbumResponseCommentDto {
    private int id;
    private String content;
    private MemberInfoDto memberInfo;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    public AlbumResponseCommentDto(int id, String content, MemberInfoDto memberInfo, LocalDateTime createdAt) {
        this.id = id;
        this.memberInfo = memberInfo;
        this.content = content;
        this.createdAt = createdAt;
    }
}
