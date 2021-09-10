package com.j2kb.minipetpee.api.fancomment.controller.dto;

import com.j2kb.minipetpee.api.fancomment.domain.FanComment;
import com.j2kb.minipetpee.global.dto.PageResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class FanCommentPaginationResponse {
    private final List<FanCommentResponse> content;
    private final PageResponse page;

    public FanCommentPaginationResponse(Page<FanComment> fanComments) {
        this.content = fanComments.stream()
            .map(FanCommentResponse::new)
            .collect(Collectors.toList());
        this.page = new PageResponse(fanComments);
    }
}
