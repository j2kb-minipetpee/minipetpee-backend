package com.j2kb.minipetpee.api.main.controller.dto.response;

import com.j2kb.minipetpee.global.controller.dto.response.PageResponse;
import com.j2kb.minipetpee.global.domain.Post;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class PopularPostPaginationResponse {
    private List<PopularPostResponse> content;
    private final PageResponse page;

    public PopularPostPaginationResponse(Page<Post> popularPosts) {
        if (!Objects.isNull(popularPosts)) {
            this.content = popularPosts
                    .stream()
                    .map(PopularPostResponse::new)
                    .collect(Collectors.toList());
        }
        this.page = new PageResponse(popularPosts);
    }
}
