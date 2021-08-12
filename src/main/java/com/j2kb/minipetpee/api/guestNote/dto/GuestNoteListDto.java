package com.j2kb.minipetpee.api.guestNote.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GuestNoteListDto {
    private int id;
    private MemberInfoDto memberInfo;
    private String content;
    private boolean visible;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    public GuestNoteListDto(int id, MemberInfoDto memberInfo, String content, boolean visible, LocalDateTime createdAt) {
        this.id = id;
        this.memberInfo = memberInfo;
        this.content = content;
        this.visible = visible;
        this.createdAt = createdAt;
    }
}
