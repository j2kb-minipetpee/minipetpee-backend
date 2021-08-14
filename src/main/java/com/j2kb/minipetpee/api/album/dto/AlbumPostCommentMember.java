package com.j2kb.minipetpee.api.album.dto;

import lombok.Data;

@Data
public class AlbumPostCommentMember {
    private int id;
    private String name;

    public AlbumPostCommentMember(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
