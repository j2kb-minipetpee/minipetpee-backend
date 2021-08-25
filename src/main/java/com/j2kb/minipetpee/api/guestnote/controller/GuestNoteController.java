package com.j2kb.minipetpee.api.guestnote.controller;

import com.j2kb.minipetpee.api.guestnote.controller.dto.request.SaveGuestNoteRequest;
import com.j2kb.minipetpee.api.guestnote.controller.dto.request.UpdateGuestNoteRequest;
import com.j2kb.minipetpee.api.guestnote.controller.dto.response.GuestNoteResponse;
import com.j2kb.minipetpee.api.guestnote.controller.dto.response.SaveGuestNoteResponse;
import com.j2kb.minipetpee.api.guestnote.domain.GuestNote;
import com.j2kb.minipetpee.api.guestnote.service.GuestNoteService;
import com.j2kb.minipetpee.api.homepee.service.HomepeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/apis/{homepee-id}/guest/guest-notes")
public class GuestNoteController {

    private final HomepeeService homepeeService;
    private final GuestNoteService guestNoteService;

    //방명록 조회
    @GetMapping
    public ResponseEntity<List<GuestNoteResponse>> findGuestNote(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @PageableDefault(size = 4, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        //homepeeId에 해당하는 homepee 존재하는지 조회
        homepeeService.findById(homepeeId);

        //homepeeId에 해당하는 방명록 조회
        List<GuestNoteResponse> guestNoteResponses = guestNoteService.findGuestNotes(homepeeId, pageable)
                .stream()
                .map(GuestNoteResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(guestNoteResponses);
    }

    //방명록 작성
    @PostMapping
    public ResponseEntity<SaveGuestNoteResponse> saveGuestNote(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @Valid @RequestBody SaveGuestNoteRequest guestNoteRequest
    ) {
        //저장
        GuestNote guestNote = guestNoteService.saveGuestNote(homepeeId, guestNoteRequest);
        return ResponseEntity.ok(new SaveGuestNoteResponse(guestNote));
    }

    //방명록 수정
    @PutMapping("/{guest-note-id}")
    public ResponseEntity<Void> updateGuestNote(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @PathVariable(name = "guest-note-id") Long guestNoteId,
            @Valid @RequestBody UpdateGuestNoteRequest updateGuestNote
    ) {
        //homepeeId에 해당하는 homepee 존재하는지 조회
        homepeeService.findById(homepeeId);

        //수정 권한 체크 추가하기(토큰값으로)
        guestNoteService.updateGuestNote(guestNoteId, updateGuestNote);
        return ResponseEntity.noContent().build();
    }

    //방명록 삭제
    @DeleteMapping("/{guest-note-id}")
    public ResponseEntity<Void> deleteGuestNote(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @PathVariable(name = "guest-note-id") Long guestNoteId
    ) {
        //homepeeId에 해당하는 homepee 존재하는지 조회
        homepeeService.findById(homepeeId);

        //삭제 권한 체크 추가하기(토큰값으로)
        guestNoteService.deleteGuestNote(guestNoteId);
        return ResponseEntity.noContent().build();
    }
}
