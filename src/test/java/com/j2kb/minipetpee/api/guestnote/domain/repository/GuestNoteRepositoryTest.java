package com.j2kb.minipetpee.api.guestnote.domain.repository;

import com.j2kb.minipetpee.api.guestnote.domain.GuestNote;
import com.j2kb.minipetpee.api.homepee.domain.Homepee;
import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.setting.domain.Tab;
import com.j2kb.minipetpee.api.setting.domain.Type;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class GuestNoteRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired private GuestNoteRepository guestNoteRepository;

    @Test
    void findByHomepeeId() {
        Member homepeeOwnner = Member.builder()
                .email("emailEx@gmail.com")
                .password("1111")
                .name("member1")
                .build();
        em.persist(homepeeOwnner);

        Member guestMember = Member.builder()
                .email("emailEx2@gmail.com")
                .password("2222")
                .name("member2")
                .build();
        em.persist(guestMember);

        Tab tab = Tab.builder()
                .type(Type.GUEST)
                .visible(true)
                .build();

        //cascade.all
        Homepee homepee = Homepee.builder()
                .member(homepeeOwnner)
                .title("title11")
                .visitCount(3)
                .build();

        homepee.setTab(tab);
        em.persist(homepee);

        GuestNote guestNote = GuestNote.builder()
                .content("방명록 입니다.")
                .visible(true)
                .tab(tab)
                .member(guestMember)
                .build();

       em.persist(guestNote);

        PageRequest result = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "id"));
        //findGuestNote 리스트에서 tab의 visible 같은 것에 접근하려면 이것들은 프록시기 때문에 guest 조회하면서 함께 가져오기 위해 fetch join 사용
        Slice<GuestNote> findGuestNote = guestNoteRepository.findByHomepeeId(homepee.getId(),result);

        List<GuestNote> content = findGuestNote.getContent();

        assertEquals(findGuestNote.getNumber(), 0);

        assertEquals(content.size(), 1);
        assertEquals(content.get(0).getId(), guestNote.getId());
        assertEquals(content.get(0).getMember().getId(), guestMember.getId());
        assertEquals(content.get(0).getMember().getName(), guestMember.getName());
        assertEquals(content.get(0).getMember().getProfileImageUrl(), homepeeOwnner.getProfileImageUrl());
        assertEquals(content.get(0).getContent(), guestNote.getContent());
        assertEquals(content.get(0).isVisible(), guestNote.isVisible());

        assertEquals(content.get(0).getTab().getHomepee().getId(), homepee.getId());
        assertEquals(content.get(0).getTab().isVisible(), guestNote.getTab().isVisible());
    }
}