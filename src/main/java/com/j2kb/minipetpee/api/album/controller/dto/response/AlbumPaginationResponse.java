package com.j2kb.minipetpee.api.album.controller.dto.response;

import com.j2kb.minipetpee.api.album.domain.AlbumPost;
import com.j2kb.minipetpee.global.dto.PageResponse;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class AlbumPaginationResponse {

    private List<AlbumPostResponse> content;
    private final PageResponse page;

    public AlbumPaginationResponse(Page<AlbumPost> albumPosts) {
        if (!Objects.isNull(albumPosts)) {
            this.content = albumPosts.stream()
                    .map(AlbumPostResponse::new)
                    .collect(Collectors.toList());
        }
        this.page = new PageResponse(albumPosts);
    }
}
