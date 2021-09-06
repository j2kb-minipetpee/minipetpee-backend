package com.j2kb.minipetpee.api.board.domain;

import com.j2kb.minipetpee.api.setting.domain.Tab;
import com.j2kb.minipetpee.global.domain.Post;
import org.hibernate.annotations.ColumnDefault;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

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

    public void updateViewCount() {
        this.viewCount = getViewCount() + 1;
    }

    public void updatePostContent(String content) {
        this.content = content;
    }

    @Builder
    public BoardPost(String title, Tab tab, String content, boolean visible) {
        super(title, tab);
        this.content = content;
    }

}