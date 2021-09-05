package com.j2kb.minipetpee.api.guestnote.service;

import com.j2kb.minipetpee.api.guestnote.controller.dto.request.SaveGuestNoteRequest;
import com.j2kb.minipetpee.api.guestnote.controller.dto.request.UpdateGuestNoteRequest;
import com.j2kb.minipetpee.api.guestnote.domain.GuestNote;
import com.j2kb.minipetpee.api.guestnote.repository.GuestNoteRepository;
import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.member.repository.MemberRepository;
import com.j2kb.minipetpee.api.setting.domain.Tab;
import com.j2kb.minipetpee.api.setting.domain.Type;
import com.j2kb.minipetpee.api.setting.repository.TabRepository;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.exception.ServiceException;
import com.j2kb.minipetpee.security.jwt.JwtAuthenticationPrincipal;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GuestNoteService {

    private final GuestNoteRepository guestNoteRepository;
    private final MemberRepository memberRepository;
    private final TabRepository tabRepository;

    @Transactional(readOnly = true)
    public Page<GuestNote> findGuestNotes(Long homepeeId, JwtAuthenticationPrincipal principal, Pageable pageable) {
        //homepeeId에 해당하는 Tab 조회
        Tab tab = tabRepository.findByHomepeeIdAndType(homepeeId, Type.GUEST)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP6007));

        if (!tab.isVisible() && (!homepeeId.equals(principal.getHomepeeId()) || Objects.isNull(principal))) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP6008);
        }

        return guestNoteRepository.findAllByTabId(tab.getId(), pageable);
    }

    @Transactional
    public GuestNote saveGuestNote(Long homepeeId, SaveGuestNoteRequest guestNoteRequest) {
        //member 객체 찾기
        Member member = memberRepository.findById(guestNoteRequest.getMemberId())
                .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP2001));
        //Tab 찾기
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

    @Transactional
    public void updateGuestNote(Long homepeeId, Long currentUserId, Long guestNoteId, UpdateGuestNoteRequest updateGuestNote) {
        //homepeeId에 해당하는 Tab 조회
        Tab tab = tabRepository.findByHomepeeIdAndType(homepeeId, Type.GUEST)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP6007));
        //guestNote 찾기
        GuestNote guestNote = guestNoteRepository.findById(guestNoteId)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND,  ErrorCode.EMP6002));

        // 방명록 작성자만 수정 가능
        if (!guestNote.memberId().equals(currentUserId)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP6009);
        }

        //update 로직
        guestNote.updateGuestNote(updateGuestNote);
    }

    @Transactional
    public void deleteGuestNote(Long homepeeId, Long currentUserId, Long guestNoteId) {
        //homepeeId에 해당하는 Tab 조회
        Tab tab = tabRepository.findByHomepeeIdAndType(homepeeId, Type.GUEST)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP6007));
        //guestNote 찾기
        GuestNote guestNote = guestNoteRepository.findById(guestNoteId)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, ErrorCode.EMP6003));
        // 방명록 작성자만 삭제 가능
        if (!guestNote.memberId().equals(currentUserId)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP6009);
        }
        //delete 로직
        guestNoteRepository.deleteById(guestNoteId);
    }
}
