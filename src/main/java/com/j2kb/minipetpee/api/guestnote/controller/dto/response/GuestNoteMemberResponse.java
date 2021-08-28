package com.j2kb.minipetpee.api.guestnote.controller.dto.response;

import com.j2kb.minipetpee.api.guestnote.domain.GuestNote;
import lombok.Getter;

@Getter
public class GuestNoteMemberResponse {
    private final Long id;
    private final String name;
    private final String profileImageUrl;

    public GuestNoteMemberResponse(GuestNote guestNote) {
        this.id = guestNote.memberId();
        this.name = guestNote.memberName();
        this.profileImageUrl = guestNote.memberProfileImageUrl();
    }
}
