package com.j2kb.minipetpee.api.guestNote;

import com.j2kb.minipetpee.api.guestNote.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/apis/{homepee-id}/guest/guest-notes")
public class GuestNoteController {

    //방명록 조회
    @GetMapping
    public ResponseEntity<List<GuestNoteListDto>> lookUpGuestNote(@PathVariable(name = "homepee-id") int hompeeId) {
        MemberInfoDto mem1 = new MemberInfoDto(1,"mem1", "url1111");
        MemberInfoDto mem2 = new MemberInfoDto(2,"mem2", "url2222");
        MemberInfoDto mem3 = new MemberInfoDto(3,"mem3", "url3333");
        GuestNoteListDto guestNote1 = new GuestNoteListDto(0,mem1,"방명록1", true, LocalDateTime.now());
        GuestNoteListDto guestNote2 = new GuestNoteListDto(1,mem2,"방명록2", true, LocalDateTime.now());
        GuestNoteListDto guestNote3 = new GuestNoteListDto(2,mem3,"방명록3", false, LocalDateTime.now());

        List<GuestNoteListDto> guestNoteLists = new ArrayList<>();
        guestNoteLists.add(guestNote1);
        guestNoteLists.add(guestNote2);
        guestNoteLists.add(guestNote3);
        return ResponseEntity.ok(guestNoteLists);
    }

    //방명록 작성
    @PostMapping
    public ResponseEntity<NewGuestNoteResponseDto> saveGuestNote(@PathVariable(name = "homepee-id") int hompeeId,
                                                                 @RequestBody NewGuestNoteRequestDto newGuestNoteRequest) {
        MemberInfoDto mem = new MemberInfoDto(newGuestNoteRequest.getMemberId(),"mem1", "url1111");
        NewGuestNoteResponseDto newGuestNoteResponse =
                new NewGuestNoteResponseDto(0, mem, newGuestNoteRequest.getContent(), newGuestNoteRequest.isVisible(), LocalDateTime.now());
        return ResponseEntity.ok(newGuestNoteResponse);
    }

    //방명록 수정
    @PutMapping("/{guest-note-id}")
    public ResponseEntity updateGuestNote(@PathVariable(name = "homepee-id") int hompeeId, @PathVariable(name = "guest-note-id") int guestNoteId, @RequestBody UpdateGuestNoteDto updateGuestNote) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //방명록 삭제
    @DeleteMapping("/{guest-note-id}")
    public ResponseEntity delteGuestNote(@PathVariable(name = "homepee-id") int hompeeId, @PathVariable(name = "guest-note-id") int guestNoteId) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
