package com.j2kb.minipetpee.global.domain;

import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.member.domain.Profile;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.*;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
public class Comment extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Long memberId() {
        if(Objects.isNull(member)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP2001);
        }
        return member.getId();
    }

    public Profile memberProfile() {
        if (Objects.isNull(member)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP2001);
        }
        return member.getProfile();
    }

    public String memberName() {
        Profile profile = memberProfile();
        if(Objects.isNull(profile)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP2002);
        }
        return profile.getName();
    }
}
