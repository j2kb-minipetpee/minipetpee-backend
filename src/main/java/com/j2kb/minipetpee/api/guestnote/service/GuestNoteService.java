package com.j2kb.minipetpee.api.guestnote.service;

import com.j2kb.minipetpee.api.guestnote.controller.dto.request.SaveGuestNoteRequest;
import com.j2kb.minipetpee.api.guestnote.controller.dto.request.UpdateGuestNoteRequest;
import com.j2kb.minipetpee.api.guestnote.domain.GuestNote;
import com.j2kb.minipetpee.api.guestnote.repository.GuestNoteRepository;
import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.member.domain.repository.MemberRepository;
import com.j2kb.minipetpee.api.setting.domain.Tab;
import com.j2kb.minipetpee.api.setting.domain.Type;
import com.j2kb.minipetpee.api.setting.repository.TabRepository;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class GuestNoteService {

    private final GuestNoteRepository guestNoteRepository;
    private final MemberRepository memberRepository;
    private final TabRepository tabRepository;

    @Transactional(readOnly = true)
    public Page<GuestNote> findGuestNotes(Long homepeeId, Pageable pageable) {
         return guestNoteRepository.findAllByHomepeeId(homepeeId, pageable);
    }

    public GuestNote saveGuestNote(Long homepeeId, SaveGuestNoteRequest guestNoteRequest) {
        //member 객체 찾기 -> 예외처리 필요
        Member member = memberRepository.findById(guestNoteRequest.getMemberId())
                .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP2001));
        //Tab 찾기 -> 못 찾을 경우, '방명록 저장에 실패하였습니다.' 에러 메시지 설정
        Tab tab = tabRepository.findByHomepeeIdAndType(homepeeId, Type.GUEST)
                .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP6001));

        GuestNote guestNote = GuestNote.builder()
                .content(guestNoteRequest.getContent())
                .visible(guestNoteRequest.isVisible())
                .tab(tab)
                .member(member)
                .build();

        return guestNoteRepository.save(guestNote);
    }

    public void updateGuestNote(Long guestNoteId, UpdateGuestNoteRequest updateGuestNote) {

        //guestNote 찾기
        GuestNote guestNote = guestNoteRepository.findById(guestNoteId)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP6002));
        //update 로직
        guestNote.updateGuestNote(guestNote, updateGuestNote);
    }

    public void deleteGuestNote(Long guestNoteId) {
        //guestNote 찾기
        GuestNote guestNote = guestNoteRepository.findById(guestNoteId)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP6002));
        //delete 로직
        guestNoteRepository.deleteById(guestNoteId);
    }
}
