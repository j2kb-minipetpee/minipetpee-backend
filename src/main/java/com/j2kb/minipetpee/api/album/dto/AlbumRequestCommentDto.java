package com.j2kb.minipetpee.api.album.dto;

import lombok.Data;

@Data
public class AlbumRequestCommentDto {
    private int memberId;
    private String content;
}
