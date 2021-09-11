package com.j2kb.minipetpee.api.album.controller.dto.response;

import com.j2kb.minipetpee.api.member.domain.Member;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class AlbumPageResult {
    private final List<AlbumResult> albumResults;
    private final Member member;
    private final Page page;

    public AlbumPageResult(List<AlbumResult> albumResults, Member member, Page albumPost) {
        this.albumResults = albumResults;
        this.member = member;
        this.page = albumPost;
    }
}
