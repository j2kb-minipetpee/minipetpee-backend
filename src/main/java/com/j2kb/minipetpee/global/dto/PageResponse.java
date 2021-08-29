package com.j2kb.minipetpee.global.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class PageResponse {
    private final Integer totalPages;
    private final Long totalElements;

    public PageResponse(Page page) {
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
    }
}
