package com.j2kb.minipetpee.api.member.domain;

import com.j2kb.minipetpee.api.homepee.domain.Homepee;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.domain.BaseTimeEntity;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Embedded
    @Column(nullable = false)
    private Profile profile;

    @ColumnDefault("0")
    private boolean deleted;

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private Homepee homepee;

    private Role role = Role.OWNER;

    public Member(Long id, String email, String password, Profile profile) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.profile = profile;
        this.deleted = false;
    }

    public String profileImageUrl() {
        if (Objects.isNull(profile)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP2002);
        }
        return this.profile.getProfileImageUrl();
    }

    public String name() {
        if (Objects.isNull(profile)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP2002);
        }
        return this.profile.getName();
    }

    public Long homepeeId() {
        if (Objects.isNull(profile)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP3001);
        }
        return this.homepee.getId();
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public void isMatchPassword(PasswordEncoder passwordEncoder, String rawPassword) {
        if (!passwordEncoder.matches(rawPassword, this.password)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP1015);
        }
    }
}

