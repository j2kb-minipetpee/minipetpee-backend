package com.j2kb.minipetpee.api.album.dto;

import lombok.Data;

@Data
public class AlbumPostCommentMember {
    private int id;
    private String name;
    private String profileImageUrl;

    public AlbumPostCommentMember(int id, String name, String profileImageUrl) {
        this.id = id;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }
}
