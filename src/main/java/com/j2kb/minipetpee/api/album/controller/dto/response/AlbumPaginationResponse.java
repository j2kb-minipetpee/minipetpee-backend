package com.j2kb.minipetpee.api.album.controller.dto.response;

import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.dto.PageResponse;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class AlbumPaginationResponse {

    private final List<AlbumPostResponse> content;
    private final PageResponse page;

    public AlbumPaginationResponse(AlbumPageResult albumPageResult) {
        if (Objects.isNull(albumPageResult.getAlbumResults())){
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMP0001);
        }
        if (Objects.isNull(albumPageResult.getPage())){
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMP0001);
        }

        this.content = albumPageResult.getAlbumResults()
                .stream()
                .map(AlbumPostResponse::new)
                .collect(Collectors.toList());
        this.page = new PageResponse(albumPageResult.getPage());
    }
}
