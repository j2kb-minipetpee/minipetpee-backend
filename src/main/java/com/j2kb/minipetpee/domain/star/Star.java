package com.j2kb.minipetpee.domain.star;

import com.j2kb.minipetpee.domain.member.Member;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Star {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "star_id")
    private int id;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member starMember;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member fanMember;

    @NonNull
    @CreatedDate
    private LocalDateTime createdAt;
}
