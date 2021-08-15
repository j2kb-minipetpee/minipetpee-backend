package com.j2kb.minipetpee.api.guestNote.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class FindGuestNotesResponse {

    @JsonValue
    private final List<FindGuestNoteResponse> guestNotelist;
}
