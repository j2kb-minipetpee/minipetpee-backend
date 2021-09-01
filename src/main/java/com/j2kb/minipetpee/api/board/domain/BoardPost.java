package com.j2kb.minipetpee.api.board.domain;

import com.j2kb.minipetpee.api.setting.domain.Tab;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.domain.Image;
import com.j2kb.minipetpee.global.domain.Post;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.http.HttpStatus;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Objects;

@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("BOARD")
@Entity
public class BoardPost extends Post {

    @Column
    private String content;

    @Column(name = "view_count")
    @ColumnDefault("0")
    private int viewCount;

    @Builder
    public BoardPost(String title, Tab tab, String content) {
        super(title, tab);
        this.content = content;
    }
}