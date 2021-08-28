package com.j2kb.minipetpee.api.album.controller.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class SaveAlbumPostCommentRequest {

    @NotNull(message = "member id 값이 존재하지 않습니다.")
    @Min(1)
    private Long memberId;

    @Size(min = 1, max = 200, message = "글자수는 {min}자 이상 {max}자 이하여야 합니다.")
    private String content;
}
