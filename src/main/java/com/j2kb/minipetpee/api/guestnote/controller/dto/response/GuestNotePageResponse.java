package com.j2kb.minipetpee.api.guestnote.controller.dto.response;

import com.j2kb.minipetpee.api.guestnote.domain.GuestNote;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class GuestNotePageResponse {
    private final int totalPages;
    private final long totalElements;

    public GuestNotePageResponse(Page<GuestNote> guestNotePage) {
        this.totalPages = guestNotePage.getTotalPages();
        this.totalElements = guestNotePage.getTotalElements();
    }
}
