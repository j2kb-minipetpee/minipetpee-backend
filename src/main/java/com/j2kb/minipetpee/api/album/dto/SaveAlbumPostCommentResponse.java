package com.j2kb.minipetpee.api.album.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class SaveAlbumPostCommentResponse {
    private final Long id;
    private final String content;
    private final AlbumPostCommentMemberResponse member;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;
}
