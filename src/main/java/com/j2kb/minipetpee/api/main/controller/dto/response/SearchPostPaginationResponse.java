package com.j2kb.minipetpee.api.main.controller.dto.response;

import com.j2kb.minipetpee.global.controller.dto.response.PageResponse;
import com.j2kb.minipetpee.global.domain.Post;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class SearchPostPaginationResponse {
    private List<SearchPostResponse> content;
    private final PageResponse page;

    public SearchPostPaginationResponse(Page<Post> searchPosts) {
        if (!Objects.isNull(searchPosts)) {
            this.content = searchPosts
                    .stream()
                    .map(SearchPostResponse::new)
                    .collect(Collectors.toList());
        }
        this.page = new PageResponse(searchPosts);
    }
}
