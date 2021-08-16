package com.j2kb.minipetpee.api.guestnote.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class SaveGuestNoteResponse {

    private final Long id;
    private final GuestNoteMemberResponse member;
    private final String content;
    private final boolean visible;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime createAt;
}
