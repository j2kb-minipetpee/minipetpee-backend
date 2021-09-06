package com.j2kb.minipetpee.api.fancomment.domain;

import com.j2kb.minipetpee.api.homepee.domain.Homepee;
import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.domain.BaseTimeEntity;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
public class FanComment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "homepee_id", nullable = false)
    private Homepee homepee;

    @Column(nullable = false)
    private String content;

    public FanComment(Member member, Homepee homepee, String content) {
        this.member = member;
        this.homepee = homepee;
        this.content = content;
    }

    public Long memberId() {
        if (Objects.isNull(member)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP2001);
        }
        return member.getId();
    }

    public String memberName() {
        if (Objects.isNull(member)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP2001);
        }
        return member.getProfile().getName();
    }

    public void changeContent(String content) {
        this.content = content;
    }
}
