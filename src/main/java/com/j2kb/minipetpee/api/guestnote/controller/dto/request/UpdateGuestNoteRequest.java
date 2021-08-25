package com.j2kb.minipetpee.api.guestnote.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor
public class UpdateGuestNoteRequest {

    @NotNull(message = "member id 값이 존재하지 않습니다.")
    @Min(0)
    private Long memberId;

    @Length(min = 2, max = 200, message = "글자수는 {min}자 이상 {max}자 이하여야 합니다.")
    private String content;

    private boolean visible;

    public UpdateGuestNoteRequest(Long memberId, String content, boolean visible) {
        this.memberId = memberId;
        this.content = content;
        this.visible = visible;
    }
}
