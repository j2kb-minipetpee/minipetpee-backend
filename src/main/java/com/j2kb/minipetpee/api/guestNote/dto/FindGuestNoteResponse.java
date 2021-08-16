package com.j2kb.minipetpee.api.guestNote.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class FindGuestNoteResponse {
    private final Long id;
    private final GuestNoteMemberResponse member;
    private final String content;
    private final boolean visible;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime createdAt;
}
