package com.j2kb.minipetpee.api.setting.repository;

import com.j2kb.minipetpee.api.homepee.domain.Homepee;
import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.member.domain.Profile;
import com.j2kb.minipetpee.api.setting.domain.Tab;
import com.j2kb.minipetpee.api.setting.domain.Type;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.exception.ServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class TabRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    private TabRepository tabRepository;

    @Test
    void findMyHomepeeId() {
        Profile profile = Profile.builder()
                .name("파트라슈")
                .build();
        Member member = Member.builder()
                .email("emailExample@gmail.com")
                .password("exexex")
                .profile(profile)
                .build();
        em.persist(member);

        Tab tab = Tab.builder()
                .type(Type.GUEST)
                .visible(true)
                .build();

        Homepee homepee = Homepee.builder()
                .member(member)
                .title("titleExample...")
                .visitCount(3)
                .build();

        homepee.addTabs(tab);
        em.persist(homepee);

        //homepeeId로 tab 찾기
        Tab result = tabRepository.findByHomepeeIdAndType(homepee.getId(),Type.GUEST)
                .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP6001));

        assertEquals(result.getType(), tab.getType());
        assertEquals(result.isVisible(), tab.isVisible());
    }
}