package com.j2kb.minipetpee.api.guestnote.controller.dto.response;

import com.j2kb.minipetpee.api.guestnote.domain.GuestNote;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GuestNoteMemberResponse {
    private final Long id;
    private final String name;
    private final String profileImageUrl;

    public GuestNoteMemberResponse(GuestNote guestNote) {
        this.id = guestNote.getMember().getId();
        this.name = guestNote.getMember().getName();
        this.profileImageUrl = guestNote.getMember().getProfileImageUrl();
    }
}
