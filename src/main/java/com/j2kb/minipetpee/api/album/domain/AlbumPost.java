package com.j2kb.minipetpee.api.album.domain;

import com.j2kb.minipetpee.api.setting.domain.Tab;
import com.j2kb.minipetpee.global.domain.Image;
import com.j2kb.minipetpee.global.domain.Post;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@DiscriminatorValue("ALBUM")
@Entity
public class AlbumPost extends Post {

    @Builder
    public AlbumPost(String title, Tab tab) {
        super(title, tab);
    }

}