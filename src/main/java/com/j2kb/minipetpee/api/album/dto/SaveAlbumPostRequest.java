package com.j2kb.minipetpee.api.album.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.j2kb.minipetpee.api.board.dto.BoardPostImage;
import lombok.Data;

import java.util.List;

@Data
public class SaveAlbumPostRequest {
    private String title;
    private List<SaveAlbumPostImage> images;
    private boolean visible;

}
