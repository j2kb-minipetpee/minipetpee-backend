package com.j2kb.minipetpee.api.main.controller.dto.response;

import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Objects;

@Getter
@RequiredArgsConstructor
public class MemberResponse {
    private final Long id;
    private final String name;
    private final String profileImageUrl;

    public MemberResponse(Member member) {
        this.id = member.getId();
        this.name = member.name();
        this.profileImageUrl = member.profileImageUrl();
    }
}