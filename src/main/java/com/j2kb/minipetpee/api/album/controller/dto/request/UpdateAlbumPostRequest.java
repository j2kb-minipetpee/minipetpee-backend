package com.j2kb.minipetpee.api.album.controller.dto.request;

import com.j2kb.minipetpee.global.domain.Image;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateAlbumPostRequest {

    @NotNull(message = "EMP5007")
    private Long id;

    @NotEmpty(message = "EMP5003")
    private String title;

    @NotEmpty(message = "EMP5004")
    @Size(max=5, message = "EMP5005")
    private List<String> images;

    public List<Image> toEntity() {
        return images.stream()
                .map(Image::new)
                .collect(Collectors.toList());
    }
}
