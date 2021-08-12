package com.j2kb.minipetpee.api.album.dto;

import com.j2kb.minipetpee.api.board.dto.ImageDto;
import lombok.Data;

import java.util.List;

@Data
public class UpdateAlbumDto {
    private int id;
    private String title;
    private List<ImageDto> imageList;
    private boolean visible;
}
