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
public class FanPaginationResponse {
    private final List<FanResponse> content;
    private final PageResponse page;

    public FanPaginationResponse(Page<Star> fanPage) {
        if (Objects.isNull(fanPage)) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMP0001);
        }
        this.content = fanPage.stream()
                .map(FanResponse::new)
                .collect(Collectors.toList());
        this.page = new PageResponse(fanPage);
    }
}
