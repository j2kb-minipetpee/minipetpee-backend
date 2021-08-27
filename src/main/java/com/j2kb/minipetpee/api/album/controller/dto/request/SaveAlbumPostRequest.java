package com.j2kb.minipetpee.api.album.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@NoArgsConstructor
public class SaveAlbumPostRequest {
    @NotBlank(message = "제목 값이 존재하지 않습니다.")
    private String title;

    @Size(min=1, max=5, message = "사진은 {min}장 이상 {max}장 이하까지 올릴 수 있습니다.")
    private List<String> images;
}
