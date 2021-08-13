package com.j2kb.minipetpee.api.guestNote.dto;

import java.util.List;

public class FindGuestNoteListResponse {

    private List<FindGuestNoteResponse> guestNotelist;

    public FindGuestNoteListResponse(List<FindGuestNoteResponse> guestNotelist) {
        this.guestNotelist = guestNotelist;
    }
}
