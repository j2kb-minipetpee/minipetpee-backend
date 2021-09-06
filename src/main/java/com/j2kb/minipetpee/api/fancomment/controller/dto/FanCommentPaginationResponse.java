package com.j2kb.minipetpee.api.fancomment.controller.dto;

import com.j2kb.minipetpee.api.fancomment.domain.FanComment;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class FanCommentPaginationResponse {
    private final List<FanCommentResponse> fanComments;

    public FanCommentPaginationResponse(List<FanComment> fanComments) {
        this.fanComments = fanComments.stream()
            .map(FanCommentResponse::new)
            .collect(Collectors.toList());
    }
}
