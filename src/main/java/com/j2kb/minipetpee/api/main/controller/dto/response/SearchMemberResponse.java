package com.j2kb.minipetpee.api.main.controller.dto.response;

import com.j2kb.minipetpee.api.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class SearchMemberResponse {
    private final Long id;
    private final String name;
    private String profileImageUrl;
    private final long homepeeId;

    public SearchMemberResponse(Member member) {
        this.id = member.getId();
        this.name = member.name();
        this.profileImageUrl = member.profileImageUrl();
        this.homepeeId = member.getHomepee().getId();
    }
}
