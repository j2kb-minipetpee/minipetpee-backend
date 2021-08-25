package com.j2kb.minipetpee.api.guestnote.controller.dto.response;

import com.j2kb.minipetpee.api.guestnote.domain.GuestNote;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Getter
public class GuestNotePaginationResponse {
    private List<GuestNoteResponse> content;
    private final GuestNotePageResponse page;

    public GuestNotePaginationResponse(Page<GuestNote> guestNotePage) {
        if (!Objects.isNull(guestNotePage)) {
            this.content = guestNotePage
                    .stream()
                    .map(GuestNoteResponse::new)
                    .collect(Collectors.toList());
        }
        this.page = new GuestNotePageResponse(guestNotePage);
    }
}
