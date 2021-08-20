package com.j2kb.minipetpee.api.guestnote.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.j2kb.minipetpee.api.guestnote.domain.GuestNote;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@RequiredArgsConstructor
public class GuestNoteResponse {
    private final Long id;
    private GuestNoteMemberResponse member;
    private final String content;
    private final boolean visible;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    public GuestNoteResponse(GuestNoteMemberResponse member, GuestNote guestNote) {
        this.id = guestNote.getId();
        if(!Objects.isNull(member)) {
            this.member = member;
        }
        this.content = guestNote.getContent();
        this.visible = guestNote.isVisible();
        this.createdAt = guestNote.getCreatedAt();
    }
}
