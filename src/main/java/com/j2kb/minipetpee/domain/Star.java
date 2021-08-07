package com.j2kb.minipetpee.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
public class Star {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "star_id")
    private int id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member starMember;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member fanMember;

    @NotNull
    @CreatedDate
    private LocalDateTime createdAt;
}
