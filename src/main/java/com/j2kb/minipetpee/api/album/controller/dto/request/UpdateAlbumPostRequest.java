package com.j2kb.minipetpee.api.album.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateAlbumPostRequest {

    @NotNull(message = "id 값이 존재하지 않습니다.")
    @Min(1)
    private Long id;

    @NotEmpty(message = "제목 걊이 존재하지 않습니다.")
    private String title;

    @Size(min=1, max=5, message = "사진은 {min}장 이상 {max}장 이하까지 올릴 수 있습니다.")
    private List<String> images;
}
