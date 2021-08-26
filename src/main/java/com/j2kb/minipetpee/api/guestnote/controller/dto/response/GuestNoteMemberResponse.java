package com.j2kb.minipetpee.api.guestnote.controller.dto.response;

import com.j2kb.minipetpee.api.guestnote.domain.GuestNote;
import com.j2kb.minipetpee.api.homepee.domain.Homepee;
import com.j2kb.minipetpee.api.member.domain.Profile;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

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
