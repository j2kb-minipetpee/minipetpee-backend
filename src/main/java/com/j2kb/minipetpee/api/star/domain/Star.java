package com.j2kb.minipetpee.api.star.domain;

import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Entity
public class Star {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "star_member_id", nullable = false)
    private Member starMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fan_member_id", nullable = false)
    private Member fanMember;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Star(Member starMember, Member fanMember) {
        this.starMember = starMember;
        this.fanMember = fanMember;
    }

    public String fanMemberName() {
        if (Objects.isNull(fanMember)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP8003);
        }
        return fanMember.name();
    }

    public String starMemberName() {
        if (Objects.isNull(starMember)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP8001);
        }
        return starMember.name();
    }

    public String starMemberProfileImageUrl() {
        if (Objects.isNull(starMember)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP8001);
        }
        return starMember.profileImageUrl();
    }

    public Long starMemberId() {
        if (Objects.isNull(starMember)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP8001);
        }
        return starMember.getId();
    }

    public String fanMemberProfileImageUrl() {
        if (Objects.isNull(fanMember)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP8001);
        }
        return fanMember.profileImageUrl();
    }

    public Long fanMemberId() {
        if (Objects.isNull(fanMember)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP8001);
        }
        return fanMember.getId();
    }
}
