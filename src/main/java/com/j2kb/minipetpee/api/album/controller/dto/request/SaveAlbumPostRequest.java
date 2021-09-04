package com.j2kb.minipetpee.api.album.controller.dto.request;

import com.j2kb.minipetpee.api.album.domain.AlbumPost;
import com.j2kb.minipetpee.api.setting.domain.Tab;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaveAlbumPostRequest {
    @NotEmpty(message = "EMP5003")
    private String title;

    @NotEmpty(message = "EMP5004")
    @Size(max=5, message = "EMP5005")
    private List<String> images;

    public AlbumPost toEntity(Tab tab) {
        return AlbumPost.builder()
                .title(this.title)
                .tab(tab)
                .build();
    }
}
