package com.j2kb.minipetpee.api.guestnote.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class SaveGuestNoteRequest {

    private Long memberId;

    @Size(min = 1, max = 200, message = "방명록은 1 ~ 200자 이어야 합니다.")
    private String content;
    private boolean visible;

    public SaveGuestNoteRequest(Long memberId, String content, boolean visible) {
        this.memberId = memberId;
        this.content = content;
        this.visible = visible;
    }
}
