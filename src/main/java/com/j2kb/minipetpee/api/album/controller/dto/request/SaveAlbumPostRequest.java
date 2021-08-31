package com.j2kb.minipetpee.api.album.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@NoArgsConstructor
public class SaveAlbumPostRequest {
    @NotEmpty(message = "EMP5004")
    private String title;

    @NotEmpty(message = "EMP5005")
    @Size(max=5, message = "EMP5006")
    private List<String> images;
}
