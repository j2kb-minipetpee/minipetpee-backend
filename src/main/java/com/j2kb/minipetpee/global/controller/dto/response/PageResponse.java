package com.j2kb.minipetpee.global.controller.dto.response;

import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class PageResponse {
    private final int totalPages;
    private final long totalElements;

    public PageResponse(Page page) {
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
    }
}
