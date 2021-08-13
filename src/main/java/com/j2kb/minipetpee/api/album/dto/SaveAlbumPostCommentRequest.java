package com.j2kb.minipetpee.api.album.dto;

import lombok.Data;

@Data
public class SaveAlbumPostCommentRequest {
    private int memberId;
    private String content;
}
