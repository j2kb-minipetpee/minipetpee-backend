package com.j2kb.minipetpee.api.guestnote.service;

import com.j2kb.minipetpee.api.guestnote.controller.dto.request.SaveGuestNoteRequest;
import com.j2kb.minipetpee.api.guestnote.domain.GuestNote;
import com.j2kb.minipetpee.api.guestnote.repository.GuestNoteRepository;
import com.j2kb.minipetpee.api.homepee.domain.Homepee;
import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.member.domain.repository.MemberRepository;
import com.j2kb.minipetpee.api.setting.domain.Tab;
import com.j2kb.minipetpee.api.setting.domain.Type;
import com.j2kb.minipetpee.api.setting.repository.TabRepository;
import com.j2kb.minipetpee.global.exception.ServiceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GuestNoteServiceTest {

    @Mock
    private GuestNoteRepository guestNoteRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private TabRepository tabRepository;

    @InjectMocks
    private GuestNoteService guestNoteService;

    @Test
    @DisplayName("방명록 정상 저장")
    void saveGuestNoteSuccessfully() {
        final Long homepeeId = 1L;
        final SaveGuestNoteRequest saveGuestNote = new SaveGuestNoteRequest(11L, "content", true);

        Member guest = Member.builder()
                .id(saveGuestNote.getMemberId())
                .email("email")
                .password("password")
                .build();

        Homepee homepee = Homepee.builder()
                .id(homepeeId)
                .build();

        Tab tab = Tab.builder()
                .id(12L)
                .type(Type.GUEST)
                .homepee(homepee)
                .build();

        GuestNote guestNote = GuestNote.builder()
                .content(saveGuestNote.getContent())
                .visible(saveGuestNote.isVisible())
                .tab(tab)
                .member(guest)
                .build();

        given(memberRepository.findById(saveGuestNote.getMemberId())).willReturn(Optional.of(guest));
        given(tabRepository.findByHomepeeIdAndType(homepeeId, Type.GUEST)).willReturn(Optional.of(tab));
        given(guestNoteRepository.save(any(GuestNote.class))).willReturn(guestNote);

        GuestNote savedGuestNote = guestNoteService.saveGuestNote(homepeeId, saveGuestNote);
        assertNotNull(savedGuestNote);
        verify(memberRepository).findById(anyLong());
        verify(tabRepository).findByHomepeeIdAndType(anyLong(), any(Type.class));
        verify(guestNoteRepository).save(any(GuestNote.class));
    }

    @Test
    @DisplayName("member 객체 찾기 오류 발생")
    void saveGuestNoteMemberNotFound() {
        final SaveGuestNoteRequest saveGuestNote = new SaveGuestNoteRequest(11L, "content", true);

        given(memberRepository.findById(anyLong())).willReturn(Optional.empty());
        assertThrows(ServiceException.class,
                () -> guestNoteService.saveGuestNote(anyLong(), saveGuestNote));
    }

    @Test
    @DisplayName("tab 객체 찾기 오류 발생")
    void saveGuestNoteTabNotFound() {
        final Long homepeeId = 1L;
        final SaveGuestNoteRequest saveGuestNote = new SaveGuestNoteRequest(11L, "content", true);

        Member guest = Member.builder()
                .id(1L)
                .email("email")
                .password("password")
                .build();

        given(memberRepository.findById(anyLong())).willReturn(Optional.of(guest));
        given(tabRepository.findByHomepeeIdAndType(homepeeId, Type.GUEST)).willReturn(Optional.empty());
        assertThrows(ServiceException.class,
                () -> guestNoteService.saveGuestNote(homepeeId, saveGuestNote));
    }
}