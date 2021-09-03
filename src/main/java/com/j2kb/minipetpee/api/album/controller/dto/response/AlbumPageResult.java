package com.j2kb.minipetpee.api.album.controller.dto.response;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class AlbumPageResult {
    private final List<AlbumResult> albumResults;
    private final Page page;

    public AlbumPageResult(List<AlbumResult> albumResults, Page albumPost) {
        this.albumResults = albumResults;
        this.page = albumPost;
    }
}
