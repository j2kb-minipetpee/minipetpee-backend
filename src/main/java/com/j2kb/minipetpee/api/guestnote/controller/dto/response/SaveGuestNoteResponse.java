package com.j2kb.minipetpee.api.guestnote.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.j2kb.minipetpee.api.guestnote.domain.GuestNote;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SaveGuestNoteResponse {

    private final Long id;
    private final GuestNoteMemberResponse member;
    private final String content;
    private final boolean visible;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    public SaveGuestNoteResponse(GuestNote guestNote) {
        this.id = guestNote.getId();
        this.member = new GuestNoteMemberResponse(guestNote);
        this.content = guestNote.getContent();
        this.visible = guestNote.isVisible();
        this.createdAt = guestNote.getCreatedAt();
    }
}
