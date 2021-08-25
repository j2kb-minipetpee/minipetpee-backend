package com.j2kb.minipetpee.api.guestnote.controller.dto.response;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class GuestNotePaginationResponse {
    private final List<GuestNoteResponse> content;
    private final int totalPage;
    private final long totalElements;

    public GuestNotePaginationResponse(List<GuestNoteResponse> content, int totalPage, long totalElements) {
        this.content = new ArrayList<>(content);
        this.totalPage = totalPage;
        this.totalElements = totalElements;
    }
}
