package com.j2kb.minipetpee.api.album.controller.dto.response;

import com.j2kb.minipetpee.api.album.domain.AlbumPost;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.domain.Comment;
import com.j2kb.minipetpee.global.dto.PageResponse;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Getter
public class AlbumPaginationResponse {

    private final List<AlbumPostResponse> content;
    private final PageResponse page;

    public AlbumPaginationResponse(Page<AlbumPost> albumPosts, Map<Long, Page<Comment>> albumComments) {
        if (Objects.isNull(albumPosts)){
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMP0001);
        }
        if(Objects.isNull(albumComments)) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.EMP0001);
        }

        List<AlbumPostResponse> content = new ArrayList<>();

        albumPosts.forEach(data -> {
            if (albumComments.containsKey(data.getId())) {
                content.add(new AlbumPostResponse(data, albumComments.get(data.getId())));
            }
        });

        this.content = content;
        this.page = new PageResponse(albumPosts);
    }
}
