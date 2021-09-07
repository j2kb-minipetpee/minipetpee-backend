package com.j2kb.minipetpee.api.star.controller.dto.response;

import com.j2kb.minipetpee.api.star.domain.Star;
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
public class StarPaginationResponse {
    private final List<StarResponse> contents;
    private final PageResponse page;

    public StarPaginationResponse(Page<Star> starPage) {
        if (Objects.isNull(starPage)) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMP0001);
        }
        this.contents = starPage.stream()
                .map(StarResponse::new)
                .collect(Collectors.toList());
        this.page = new PageResponse(starPage);
    }
}
