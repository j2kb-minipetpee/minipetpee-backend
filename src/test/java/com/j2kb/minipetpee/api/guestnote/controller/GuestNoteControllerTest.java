package com.j2kb.minipetpee.api.guestnote.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.j2kb.minipetpee.api.guestnote.domain.GuestNote;
import com.j2kb.minipetpee.api.guestnote.service.GuestNoteService;
import com.j2kb.minipetpee.api.homepee.domain.Homepee;
import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.setting.domain.Tab;
import com.j2kb.minipetpee.api.setting.domain.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest(GuestNoteController.class)
class GuestNoteControllerTest {

    private final String BASE_URL = "/apis/{homepee-id}/guest/guest-notes";
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private GuestNoteService guestNoteService;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new GuestNoteController(guestNoteService))
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("방명록 조회")
    void findGuestNote() throws Exception {
        Pageable pageable =  PageRequest.of(0, 4, Sort.by("id").descending());

        GuestNote guestNote1 = GuestNote.builder()
                .id(1L)
                .content("ggg")
                .visible(true)
                .tab(Tab.builder()
                        .type(Type.GUEST)
                        .visible(true)
                        .homepee(Homepee.builder()
                                .id(1L)
                                .build())
                        .build())
                .member(Member.builder()
                        .id(1L)
                        .name("minipet")
                        .profileImageUrl("profileUrl")
                        .build())
                .build();


        GuestNote guestNote2 = GuestNote.builder()
                .id(2L)
                .content("반가워요!")
                .visible(true)
                .tab(Tab.builder()
                        .type(Type.GUEST)
                        .visible(true)
                        .homepee(Homepee.builder()
                                .id(2L)
                                .build())
                        .build())
                .member(Member.builder()
                        .id(1L)
                        .name("minipet")
                        .profileImageUrl("profileUrl")
                        .build())
                .build();

        List<GuestNote> guestNoteList = new ArrayList<>();
        guestNoteList.add(guestNote1);
        guestNoteList.add(guestNote2);

        Slice<GuestNote> result = new SliceImpl<>(guestNoteList);
        given(guestNoteService.findGuestNote(any(),eq(pageable))).willReturn(result);
        mockMvc.perform(get(BASE_URL, 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[0].member.id").value(1L))
                .andExpect(jsonPath("$.[0].member.name").value("minipet"))
                .andExpect(jsonPath("$.[0].member.profileImageUrl").value("profileUrl"))
                .andExpect(jsonPath("$.[0].content").value("ggg"))
                .andExpect(jsonPath("$.[0].visible").value(true));
    }

    @Test
    void saveGuestNote() throws Exception {
        GuestNote savedGuestNote = GuestNote.builder()
                .id(1L)
                .content("반가워요!")
                .visible(true)
                .tab(Tab.builder()
                        .type(Type.GUEST)
                        .visible(true)
                        .homepee(Homepee.builder()
                                .member(Member.builder()
                                        .email("emailExam@gmail.com")
                                        .password("1111")
                                        .name("홈피주인")
                                        .build())
                                .title("홈피주인의 홈피")
                                .visitCount(3)
                                .build())
                        .build())
                .member(Member.builder()
                        .id(3L)
                        .email("writer@gmail.com")
                        .password("2222")
                        .name("방명록글쓴이")
                        .profileImageUrl("profileUrl")
                        .build())
                .build();

        given(guestNoteService.saveGuestNote(eq(1L),any())).willReturn(savedGuestNote);

        mockMvc.perform(post(BASE_URL,1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"memberId\":1,\"content\":\"반가워요!\",\"visible\":true}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.member.id").value(3L))
                .andExpect(jsonPath("$.member.name").value("방명록글쓴이"))
                .andExpect(jsonPath("$.member.profileImageUrl").value("profileUrl"))
                .andExpect(jsonPath("$.content").value("반가워요!"))
                .andExpect(jsonPath("$.visible").value(true));

        verify(guestNoteService).saveGuestNote(eq(1L),any());
    }


    @Test
    void updateGuestNote() {

    }

    @Test
    void deleteGuestNote() {
    }
}