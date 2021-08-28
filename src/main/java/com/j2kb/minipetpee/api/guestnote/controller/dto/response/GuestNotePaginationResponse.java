package com.j2kb.minipetpee.api.guestnote.controller.dto.response;

import com.j2kb.minipetpee.api.guestnote.domain.GuestNote;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.dto.PageResponse;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Getter
public class GuestNotePaginationResponse {
    private final List<GuestNoteResponse> content;
    private final PageResponse page;

    public GuestNotePaginationResponse(Page<GuestNote> guestNotePage) {
        if (Objects.isNull(guestNotePage)) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMP0001);
        }
        this.content = guestNotePage.stream()
                .map(GuestNoteResponse::new)
                .collect(Collectors.toList());
        this.page = new PageResponse(guestNotePage);
    }
}
