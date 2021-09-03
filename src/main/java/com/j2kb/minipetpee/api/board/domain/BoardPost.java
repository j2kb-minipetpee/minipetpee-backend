package com.j2kb.minipetpee.api.board.domain;

import com.j2kb.minipetpee.global.domain.Post;
import org.hibernate.annotations.ColumnDefault;
import lombok.*;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@DiscriminatorValue("BOARD")
@Entity
public class BoardPost extends Post {

    @Column
    private String content;

    @Column(name = "view_count")
    @ColumnDefault("0")
    private int viewCount;
}