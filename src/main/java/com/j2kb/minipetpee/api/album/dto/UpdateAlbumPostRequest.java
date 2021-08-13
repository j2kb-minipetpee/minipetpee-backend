package com.j2kb.minipetpee.api.album.dto;

import com.j2kb.minipetpee.api.board.dto.BoardPostImage;
import lombok.Data;

import java.util.List;

@Data
public class UpdateAlbumPostRequest {
    private int id;
    private String title;
    private List<BoardPostImage> imageList;
    private boolean visible;
}
