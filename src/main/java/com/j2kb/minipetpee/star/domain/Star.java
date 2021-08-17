package com.j2kb.minipetpee.star.domain;

import com.j2kb.minipetpee.member.domain.Member;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

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
}
