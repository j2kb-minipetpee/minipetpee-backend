package com.j2kb.minipetpee.api.setting.domain;

import com.j2kb.minipetpee.api.homepee.domain.Homepee;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Entity
public class Tab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @ColumnDefault("1")
    private boolean visible;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "homepee_id")
    private Homepee homepee;

    public Long homepeeId() {
        if (Objects.isNull(homepee)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP3001);
        }
        return this.homepee.getId();
    }

    public void updateVisibility(boolean visible){
        // 필드 하나만 바꾸는 거라 엔티티 대신 boolean 값을 인자로 받아서 설정했는데 괜찮을까요?
        this.visible = visible;
    }
}
